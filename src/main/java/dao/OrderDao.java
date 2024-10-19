package dao;

import model.Order;

import java.sql.SQLException;
import java.util.Date;

public interface OrderDao {
    void setup() throws SQLException;
    Order addOrder(String username, float totalPrice) throws SQLException;

}
