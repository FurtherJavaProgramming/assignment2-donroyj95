package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private int id;
    private String username;
    private LocalDateTime orderDate;
    private float totalPrice;


    public Order(int orderId, String username, LocalDateTime currentDate, float totalPrice) {
        this.id = orderId;
        this.username = username;
        this.orderDate = currentDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
