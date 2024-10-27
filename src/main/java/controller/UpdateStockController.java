package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.sql.SQLException;

public class UpdateStockController extends MainController{

    @FXML
    private Button updateStock;
    @FXML
    private Button back;
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField stockField;

    public UpdateStockController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    @FXML
    public void initialize() {
        updateStock.setOnAction(e -> {
            String bookTitle = bookTitleField.getText();
            String newStock = stockField.getText();
            if(bookTitle.isEmpty()){
                super.setPromptMessage("Please enter book title");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }
            if (!newStock.matches("\\b0*[1-9]\\d*\\b")) {
                super.setPromptMessage("Please enter valid numeric value for stock");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }
            super.setPromptMessage("");

            try {
                Book book = super.getModel().getBookDao().getBook(bookTitle);
                if(book == null){
                    super.setPromptMessage("Book does not exist");
                    super.getPromptMessage().setTextFill(Color.RED);
                    return;
                }
                book.setCopies(Integer.parseInt(newStock));
                super.getModel().getBookDao().updateBookStock(book);
                super.setPromptMessage("Stock updated successfully");
                super.getPromptMessage().setTextFill(Color.GREEN);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
        back.setOnAction(e -> {
            super.navigateAdminHomePage();
        });

    }

}
