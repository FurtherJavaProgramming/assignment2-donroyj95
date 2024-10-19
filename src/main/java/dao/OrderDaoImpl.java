package dao;

import model.Book;
import model.Order;

import java.sql.*;
import java.time.LocalDate;

import static java.sql.JDBCType.DATE;

public class OrderDaoImpl implements OrderDao{
    private final String TABLE_NAME = "orders";


    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR, orderDate DATE, totalPrice FLOAT, FOREIGN KEY (username) REFERENCES users(username))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order addOrder(String username, float totalPrice) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (username, orderDate, totalPrice) VALUES (?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        Date date = new Date(currentDate.toEpochDay());
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setDate(2,date);
            stmt.setFloat(3, totalPrice);

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                return new Order(orderId,username,currentDate,totalPrice);
            } else {
                System.out.println("Auto-generated Order ID not found!");
                return null;
            }
        }
    }
}
