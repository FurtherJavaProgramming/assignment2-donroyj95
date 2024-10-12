package model;

public class Book {
    private String title = null;
    private String author = null;
    private float price = 0.0f;
    private int copies = 0;
    private int soldCopies = 0;

    public Book(String title) {
        this.title = title;
    }

    public int getSoldCopies() {
        return soldCopies;
    }

    public void setSoldCopies(int soldCopies) {
        this.soldCopies = soldCopies;
    }

    public Book(){}

    public Book(String title, String author, float price, int copies, int soldCopies) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.copies = copies;
        this.soldCopies = soldCopies;
    }

    // Getters and setters for the properties go here
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
