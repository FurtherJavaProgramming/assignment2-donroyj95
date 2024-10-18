package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomeController extends MainController {

	@FXML
	private MenuItem viewProfile;
	@FXML
	private MenuItem updateProfile;
	@FXML
	private MenuBarController menuBarIncludeController;
	@FXML
	private Label welcomeMessage;

	@FXML
	private Button selectBooks;

	@FXML
	private TableView<Book> bookTable;

	@FXML
	public TableColumn<Book, String> title;
	@FXML
	public TableColumn<Book, String> author;
	@FXML
	public TableColumn<Book, Integer> soldCopies;
	
	public HomeController(Stage parentStage, Model model) {
		super(parentStage, model);
	}

	public void initialize() {
		welcomeMessage.setText("Welcome to The Bookshop "+super.getModel().getCurrentUser().getUsername());
		menuBarIncludeController.setMenuBarState(super.getModel(),super.getStage(),super.getParentStage());

        ArrayList<Book> books = null;
        try {
            books = super.getModel().getBookDao().getHighestSellingBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		soldCopies.setCellValueFactory(new PropertyValueFactory<>("soldCopies"));

		bookTable.setItems(FXCollections.observableArrayList(books));

		selectBooks.setOnAction(event -> {
			super.navigateSelectBookPage();
			super.getStage().close();
		});

	}
}
