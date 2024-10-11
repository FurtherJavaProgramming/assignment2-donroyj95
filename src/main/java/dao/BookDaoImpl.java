package dao;


import model.Book;
import model.User;

import java.sql.*;

public class BookDaoImpl implements BookDao {
    private final String TABLE_NAME = "books";

    public BookDaoImpl() {
    }


    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (title VARCHAR NOT NULL,"
                    + "author VARCHAR, copies INTEGER, price FLOAT,  soldCopies INTEGER, PRIMARY KEY (title))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBook(String title) throws SQLException {
        return null;
    }

    @Override
    public Book[] getAllBooks() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
//        try (Connection connection = Database.getConnection();){
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            Book[] books = new Book[rs.getRow()];
//
//            while (rs.next()) {
//
//            }
//        }
        return new Book[0];
    }

    @Override
    public boolean isBookAvailable(String title)throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE title Like ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "%"+title.trim()+"%");

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }


    @Override
    public Book addBook(String title, String author,Integer copies, Float price) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?,?,?,?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, copies);
            stmt.setFloat(4, price);
            stmt.setInt(5, 0);

            stmt.executeUpdate();
            return new Book(title,author,price,copies);
        }

    };






}
