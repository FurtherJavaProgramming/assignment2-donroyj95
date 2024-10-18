package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;
import model.Model;
import model.ShoppingCartBook;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class ShoppingCartController extends MainController{

    @FXML
    private TableView<Book> shoppingCart;
    @FXML
    private Button updateCart;
    @FXML
    private Button cancel;
    @FXML
    private Button checkout;

    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, Integer> copies;
    @FXML
    private Label totalPriceLabel;

    public ShoppingCartController(Stage parentStage, Model model) {
        super(parentStage, model);
    }


    public void initialize() {
        Enumeration<Book> k = this.getModel().getShoppingCart().getBookDictionary().keys();
        ArrayList<ShoppingCartBook> shoppingCartBooks = new ArrayList<>();
        this.getModel().getShoppingCart().setShoppingCartBooks(new ArrayList<>());
        float totalPrice = 0;


        while (k.hasMoreElements()) {
            Book key = k.nextElement();
            int copies = this.getModel().getShoppingCart().getBookDictionary().get(key);
            totalPrice += key.getPrice() * copies;
            this.getModel().getShoppingCart().addBookToShoppingCart(key,copies);
        }
        this.getModel().getShoppingCart().setTotalPrice(totalPrice);
        totalPriceLabel.setText(Float.toString(totalPrice));

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        copies.setCellValueFactory(new PropertyValueFactory<>("buyingCopies"));

        shoppingCart.setItems(FXCollections.observableArrayList(this.getModel().getShoppingCart().getShoppingCartBooks()));

        updateCart.setOnAction(event -> {
            this.getStage().close();
            this.getParentStage().show();
        });

        cancel.setOnAction(event -> {
            this.getModel().getShoppingCart().setShoppingCartBooks(new ArrayList<>());
            this.getModel().getShoppingCart().setBookDictionary(new Hashtable<>());
            super.getStage().close();
            super.navigateUserHomePage();
        });

        checkout.setOnAction(event -> {
            super.getStage().close();
            super.navigateCheckoutPage();
        });
    }


}
