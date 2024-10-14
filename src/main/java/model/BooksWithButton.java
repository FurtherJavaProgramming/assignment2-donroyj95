package model;

import javafx.scene.control.Button;

public class BooksWithButton extends Book {
    Button addToCartButton;

    public BooksWithButton(Book book) {
        super.setAuthor(book.getAuthor());
        super.setTitle(book.getTitle());
        super.setCopies(book.getCopies());
        super.setPrice(book.getPrice());
        super.setSoldCopies(book.getSoldCopies());
        this.addToCartButton = new Button("Add to Cart");

    }
}
