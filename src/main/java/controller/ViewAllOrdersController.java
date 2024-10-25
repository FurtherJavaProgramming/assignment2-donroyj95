package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Book;
import model.Model;

public class ViewAllOrdersController extends MainController{

    @FXML
    private TableView<Book> viewAllOrders;

    @FXML
    public TableColumn<Book, Integer> orderId;
    @FXML
    public TableColumn<Book, String> dateTime;
    @FXML
    public TableColumn<Book, Float> totalPrice;
    @FXML
    public TableColumn<Book, String> booksAndCopies;

    public ViewAllOrdersController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

}
