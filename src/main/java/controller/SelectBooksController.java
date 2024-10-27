package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.Model;
import util.TableTextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class SelectBooksController extends MainController{

    @FXML
    private Button backButton;
    @FXML
    private Button addToCart;


    @FXML
    private TableView<Book> selectBooksTable;

    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, Integer> copies;
    @FXML
    private TableColumn<Book, Float> price;


    public SelectBooksController(Stage parentStage, Model model) {
        super(parentStage, model);
    }



    public void initialize() {
        backButton.setOnAction(event -> {
            super.getModel().getShoppingCart().setBookDictionary(new Hashtable<>());
            super.backButtonAction();
        });

        addToCart.setOnAction(event -> {
            if(super.getModel().getShoppingCart().getBookDictionary().isEmpty()){
                super.setPromptMessage("Please add at least one book to the shopping cart");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }
            super.setPromptMessage("");
            super.getStage().close();
            super.navigateShoppingCartPage();
        });

        TableColumn<Book, Void> actionCol = getEnterBookCopiesTableColumn();

        selectBooksTable.getColumns().add(actionCol);


        ArrayList<Book> books = null;
        try {
            books = super.getModel().getBookDao().getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        selectBooksTable.setItems(FXCollections.observableArrayList(books));

    }

    private TableColumn<Book, Void> getEnterBookCopiesTableColumn() {
        TableColumn<Book, Void> enterBookCopies = new TableColumn<>("Enter Book Copies");

        enterBookCopies.setCellFactory(new Callback<TableColumn<Book, Void>,
                TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(TableColumn<Book, Void> param) {
                return new TableTextField(SelectBooksController.super.getStage(),SelectBooksController.super.getModel(),
                        SelectBooksController.super.getPromptMessage());
            }
        });
        return enterBookCopies;
    }
}

