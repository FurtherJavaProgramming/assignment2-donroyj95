package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookDao;
import dao.BookDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class Model {
	private UserDao userDao;
	private BookDao bookDao;
	private User currentUser;
	private ShoppingCart shoppingCart;
	private ArrayList<Book> currentBooks;
	
	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
		shoppingCart = new ShoppingCart();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
		shoppingCart.setUser(currentUser);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public ArrayList<Book> getCurrentBooks() {
		return currentBooks;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
