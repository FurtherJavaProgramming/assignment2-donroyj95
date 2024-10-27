package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminHomePageController extends MainController {
    @FXML
    private TableView<Book> stockTable;
    @FXML
    private Button updateStock;
    @FXML
    private Button addNewBook;

    @FXML
    public TableColumn<Book, String> title;
    @FXML
    public TableColumn<Book, String> author;
    @FXML
    public TableColumn<Book, Float> price;

    @FXML
    public TableColumn<Book, Integer> soldCopies;

    @FXML
    public TableColumn<Book, Integer> copies;

    @FXML
    public void handleOpenAction(javafx.event.ActionEvent actionEvent) {
        super.navigateUserProfile();
    }

    @FXML
    private MenuBarController menuBarIncludeController;


    public AdminHomePageController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    @FXML
    public void initialize() {

        menuBarIncludeController.setMenuBarState(super.getModel(),super.getStage(),super.getParentStage());
        ArrayList<Book> books = new ArrayList<>();
        try{
            books = super.getModel().getBookDao().getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        soldCopies.setCellValueFactory(new PropertyValueFactory<>("soldCopies"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));

        stockTable.setItems(FXCollections.observableArrayList(books));


        addNewBook.setOnAction(event -> {
            try{

                FXMLLoader loader = super.Loader("/view/AddBook.fxml");
                AddBookController addBookController = new AddBookController(super.getStage(), super.getModel());

                loader.setController(addBookController);
                GridPane root = loader.load();

                addBookController.showStage(root,"Add New Book",0,0);
                super.getStage().close();

            }catch (IOException e) {
                System.out.println(e);
            }
        });


        updateStock.setOnAction(event -> {
            try{

                FXMLLoader loader = super.Loader("/view/UpdateStock.fxml");
                UpdateStockController updateStockController = new UpdateStockController(super.getStage(), super.getModel());

                loader.setController(updateStockController);
                VBox root = loader.load();

                updateStockController.showStage(root,"Update Stock",0,0);
                super.getStage().close();

            }catch (IOException e) {
                System.out.println(e);
            }
        });
    }
}
