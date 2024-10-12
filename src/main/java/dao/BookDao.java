package dao;

import model.Book;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDao {
    void setup() throws SQLException;
    Book getBook(String title) throws SQLException;
    ArrayList<Book> getAllBooks() throws SQLException;
    public Book addBook(String title, String author,Integer copies, Float price) throws SQLException;
    public boolean isBookAvailable(String title)throws SQLException;

    void updateBookStock(Book book);
}
