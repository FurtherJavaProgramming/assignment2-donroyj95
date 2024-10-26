package model;

public class BookOrder {
    private String bookTitle;
    private int orderId;
    private int bookCopies;

    public BookOrder(int orderId, String bookTitle, int bookCopies) {
        this.orderId = orderId;
        this.bookTitle = bookTitle;
        this.bookCopies = bookCopies;
    }

}

