package dao;

import model.BookOrder;
import model.OrderView;
import model.ShoppingCartBook;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class BookOrderDaoImpl implements BookOrderDao {
    private final String TABLE_NAME = "book_order";

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (bookTitle VARCHAR NOT NULL,"
                    + "orderId INTEGER NOT NULL, bookCopies INTEGER, PRIMARY KEY (bookTitle, orderId)," +
                    " FOREIGN KEY (orderId) REFERENCES orders(id)," +
                    " FOREIGN KEY (bookTitle) REFERENCES books(title))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookOrder addBookOrder(int orderId, String bookTitle, int bookCopies)throws SQLException{

        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, bookTitle);
            stmt.setInt(2, orderId);
            stmt.setInt(3, bookCopies);


            stmt.executeUpdate();
            return new BookOrder(orderId,bookTitle,bookCopies);
        }
    }


    @Override
    public void addBooksOrder(int orderId, ArrayList<ShoppingCartBook> shoppingCartBooks) throws SQLException{

        for (ShoppingCartBook shoppingCartBook : shoppingCartBooks) {
            addBookOrder(orderId,shoppingCartBook.getTitle(),shoppingCartBook.getBuyingCopies());
        }
    }


    @Override
    public ArrayList<OrderView> getBooksOrdersByUsername(String username) throws SQLException{

        String query = "SELECT O.id AS orderId,O.orderDate AS dateAndTime,O.totalPrice," +
                " GROUP_CONCAT(B.bookTitle || ' (' || B.bookCopies || ' copies' || ')\n') AS booksAndCopies " +
                "FROM orders O " +
                "JOIN book_order B ON O.id = B.orderId " +
                "WHERE O.username = ? "+
                " GROUP BY " +
                " O.id, O.orderDate, O.totalPrice";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);) {
            stmt.setString(1, username);
            ArrayList<OrderView> orderViews = new ArrayList<>();

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderView orderView = new OrderView(rs.getInt("orderId"),
                            rs.getString("dateAndTime"),
                            rs.getFloat("totalPrice"),rs.getString("booksAndCopies"));
                    orderViews.add(orderView);
                }
                return orderViews;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
