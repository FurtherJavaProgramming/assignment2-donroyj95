package dao;

import model.BookOrder;
import model.OrderView;
import model.ShoppingCartBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookOrderDao {
    void setup() throws SQLException;
    BookOrder addBookOrder(int orderId, String bookTitle, int bookCopies) throws SQLException;
    void addBooksOrder(int orderId, ArrayList<ShoppingCartBook> shoppingCartBooks) throws SQLException;
    ArrayList<OrderView> getBooksOrdersByUsername(String username) throws SQLException;
}
