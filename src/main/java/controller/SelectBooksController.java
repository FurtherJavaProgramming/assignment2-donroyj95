package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectBooksController extends MainController{

    @FXML
    private Button backButton;

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



    public void initialize() throws SQLException {
        backButton.setOnAction(event -> {
            super.backButtonAction();
        });

        ArrayList<Book> books = super.getModel().getBookDao().getAllBooks();

//        for (Book book : books) {}

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        selectBooksTable.setItems(FXCollections.observableArrayList(books));

    }
}
