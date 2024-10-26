package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

public class OrderView {
    private BooleanProperty isSelected;
    private int orderId;
    private String dateTime;
    private Float totalPrice;
    private String booksAndCopies;

    public OrderView(int orderID, String dateTime, Float totalPrice, String booksAndCopies) {
        this.isSelected=  new SimpleBooleanProperty(false);
        this.orderId = orderID;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.booksAndCopies = booksAndCopies;
    }

    public OrderView() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBooksAndCopies() {
        return booksAndCopies;
    }

    public void setBooksAndCopies(String booksAndCopies) {
        this.booksAndCopies = booksAndCopies;
    }

//    public BooleanProperty selectedProperty() {
//        return isSelected;
//    }

    public boolean isSelected() {
        return isSelected.get();
    }

    public BooleanProperty isSelectedProperty() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected.set(isSelected);
    }
}
