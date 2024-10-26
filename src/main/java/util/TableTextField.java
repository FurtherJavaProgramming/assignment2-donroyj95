package util;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Model;
import model.ShoppingCart;

import java.util.*;

import static controller.MainController.isInteger;

public class TableTextField extends TableCell<Book, Void> {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    private Label errorMessage;
    private TextField buyingCopies = new TextField();

    public TableTextField(Stage parentStage, Model model, Label errorMessage) {
        super();
        this.parentStage = parentStage;
        this.model = model;
        this.stage = new Stage();

        buyingCopies.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(buyingCopies.getText()+"1");
            Book book = getTableView().getItems().get(getIndex());
            this.model.getShoppingCart().getBookDictionary().remove(book);

            if(buyingCopies.getText().isEmpty()){
                errorMessage.setText("");
                return;
            }

            if(!isInteger(buyingCopies.getText())) {
                errorMessage.setText("Please enter a valid number");
                errorMessage.setTextFill(Color.RED);
                return;
            }
            int copies = Integer.parseInt(buyingCopies.getText());
            if(copies>book.getCopies()){
                errorMessage.setText(book.getTitle()+" has only "+book.getCopies()+" copies");
                errorMessage.setTextFill(Color.RED);
                return;
            }
            errorMessage.setText("");

            if(copies> 0){
                this.model.getShoppingCart().getBookDictionary().put(book, copies);
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        System.out.println(item + " "+empty+" "+getIndex());

        if (empty) {
            setGraphic(null);
        } else  {
            Book book = getTableView().getItems().get(getIndex());
            Integer copies = this.model.getShoppingCart().getBookDictionary().get(book);
            if(copies!=null){
                setText(String.valueOf(copies));
            }
            setGraphic(buyingCopies);
        }
    }
}

