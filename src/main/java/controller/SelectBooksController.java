package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.BooksWithButton;
import model.Model;
import util.ButtonCell;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectBooksController extends MainController{

    @FXML
    private Button backButton;

    @FXML
    private TableView<BooksWithButton> selectBooksTable;

    @FXML
    private TableColumn<BooksWithButton, String> title;
    @FXML
    private TableColumn<BooksWithButton, String> author;
    @FXML
    private TableColumn<BooksWithButton, Integer> copies;
    @FXML
    private TableColumn<BooksWithButton, Float> price;
//    @FXML
//    private TableColumn<BooksWithButton, String> addToCartButton;


    public SelectBooksController(Stage parentStage, Model model) {
        super(parentStage, model);
    }



    public void initialize() throws SQLException {
        backButton.setOnAction(event -> {
            super.backButtonAction();
        });

        TableColumn<BooksWithButton, Void> actionCol = new TableColumn<>("Action");

        actionCol.setCellFactory(new Callback<TableColumn<BooksWithButton, Void>,
                TableCell<BooksWithButton, Void>>() {
            @Override
            public TableCell<BooksWithButton, Void> call(TableColumn<BooksWithButton, Void> param) {
                return new ButtonCell();
            }
        });

        selectBooksTable.getColumns().add(actionCol);


        ArrayList<Book> books = super.getModel().getBookDao().getAllBooks();
        ArrayList<BooksWithButton> booksWithButtons = new ArrayList<BooksWithButton>();

        for (Book book : books) {
            booksWithButtons.add(new BooksWithButton(book));
        }

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        actionCol.setCellValueFactory(new PropertyValueFactory<>("addToCartButton"));

        selectBooksTable.setItems(FXCollections.observableArrayList(booksWithButtons));

    }
}

