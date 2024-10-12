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
	private ArrayList<Book> currentBooks;
	
	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public ArrayList<Book> getCurrentBooks() {
		return currentBooks;
	}

}
