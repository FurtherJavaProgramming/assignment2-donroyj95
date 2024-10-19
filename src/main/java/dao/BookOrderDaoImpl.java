package dao;

import model.BookOrder;
import model.ShoppingCartBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
}
