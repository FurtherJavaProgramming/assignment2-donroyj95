package util;

import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import model.BooksWithButton;

public class ButtonCell extends TableCell<BooksWithButton, Void> {
    private final Button addToCartButton = new Button("Add to Cart");

    public ButtonCell() {
        addToCartButton.setOnAction(event -> {
            BooksWithButton book = getTableView().getItems().get(getIndex());
            System.out.println("Add to Cart clicked for: " + book.getTitle());
            // Add your logic here for adding the book to the cart
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(addToCartButton);
        }
    }
}

// In your SelectBooksController class

