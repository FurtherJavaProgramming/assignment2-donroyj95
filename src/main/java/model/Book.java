package model;

public class Book {
    private String bookTitle;
    private String authors;
    private float price;
    private int availableCopies;

    public Book(){}

    public Book(String bookTitle, String authors, float price, int availableCopies) {
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.price = price;
        this.availableCopies = availableCopies;
    }

    // Getters and setters for the properties go here
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
