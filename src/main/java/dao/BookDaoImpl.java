package dao;


import model.Book;
import model.User;

import java.sql.*;
import java.util.ArrayList;

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
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE LOWER(title) = LOWER(?)";
        System.out.println(sql);
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, title);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(rs.getString("title"),rs.getString("author"),
                            rs.getFloat("price"),rs.getInt("copies"),
                            rs.getInt("soldCopies"));
                }
                return null;
            }

        }
    }

    @Override
    public ArrayList<Book> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = Database.getConnection();){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book(rs.getString("title"),
                        rs.getString("author"),
                        rs.getFloat("price"),
                        rs.getInt("copies"),rs.getInt("soldCopies"));
                books.add(book);
            }
        }

        return books;
    }

    @Override
    public boolean isBookAvailable(String title)throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE LOWER(title) = LOWER(?) COLLATE NOCASE";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, title.trim().toLowerCase());

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
            return new Book(title,author,price,copies,0);
        }

    };


    @Override
    public void updateBookStock(Book book){
        String query = "UPDATE books SET copies = ? WHERE title = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);) {
            stmt.setInt(1, book.getCopies());
            stmt.setString(2, book.getTitle());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
