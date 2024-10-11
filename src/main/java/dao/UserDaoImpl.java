package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";

	public UserDaoImpl() {
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR NOT NULL, firstName VARCHAR, lastName VARCHAR, isAdmin BOOLEAN, PRIMARY KEY (username))";

			String addAdminUser = "INSERT OR REPLACE INTO "+TABLE_NAME + " Values (\"admin\",\"admin\",\"Admin\",\"Admin\",true) ";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(addAdminUser);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setAdmin(rs.getBoolean("isAdmin"));

					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public User createUser(String username, String password, String firstName, String lastName,Boolean isAdmin) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?,?,?,?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setBoolean(5, isAdmin);

			stmt.executeUpdate();
			return new User(username, password);
		} 
	}

	@Override
	public Boolean isUserNameExist(String username) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
		}
	}
}
