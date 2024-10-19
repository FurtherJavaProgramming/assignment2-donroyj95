package dao;

import model.Book;
import model.ShoppingCartBook;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDao {
    void setup() throws SQLException;
    Book getBook(String title) throws SQLException;
    ArrayList<Book> getAllBooks() throws SQLException;
    public Book addBook(String title, String author,Integer copies, Integer soldCopies, Float price) throws SQLException;
    public boolean isBookAvailable(String title)throws SQLException;

    void updateBookStock(Book book);
    ArrayList<Book> getHighestSellingBooks() throws SQLException;
    void updateSoldCopies(ArrayList<ShoppingCartBook> books) throws SQLException;
    void updateBookCopies(Book book) throws SQLException;
}
