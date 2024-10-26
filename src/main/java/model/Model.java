package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.*;

public class Model {
	private UserDao userDao;
	private BookDao bookDao;
	private OrderDao orderDao;
	private BookOrderDao bookOrderDao;
	private User currentUser;
	private ShoppingCart shoppingCart;
	private ArrayList<Book> currentBooks;
	private ArrayList<OrderView> selectedOrdersToExport;
	
	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
		orderDao = new OrderDaoImpl();
		bookOrderDao = new BookOrderDaoImpl();
		shoppingCart = new ShoppingCart();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
		orderDao.setup();
		bookOrderDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
		if(currentUser != null) {
			shoppingCart.setUser(currentUser);
		}
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

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public BookOrderDao getBookOrderDao() {
		return bookOrderDao;
	}

	public void setBookOrderDao(BookOrderDao bookOrderDao) {
		this.bookOrderDao = bookOrderDao;
	}

	public void setCurrentBooks(ArrayList<Book> currentBooks) {
		this.currentBooks = currentBooks;
	}

	public ArrayList<OrderView> getSelectedOrdersToExport() {
		return selectedOrdersToExport;
	}

	public void setSelectedOrdersToExport(ArrayList<OrderView> selectedOrdersToExport) {
		this.selectedOrdersToExport = selectedOrdersToExport;
	}
}
