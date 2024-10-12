package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.sql.SQLException;


public class AddBookController extends MainController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField copiesField;
    @FXML
    private Button back;
    @FXML
    private Button addBook;
    @FXML
    private Label errorLabel;

    public AddBookController(Stage parentStage, Model model) {
        super(parentStage, model);
    }




    public void initialize() {
        addBook.setOnAction(event -> {
            if (!authorField.getText().isEmpty() &&!priceField.getText().isEmpty() &&
                    !copiesField.getText().isEmpty()&& !titleField.getText().isEmpty()) {

                if(!priceField.getText().matches("\\b(?:0|[1-9]\\d*)(?:\\.\\d+)?\\b")){
                    errorLabel.setText("Please enter a valid price");
                    errorLabel.setTextFill(Color.RED);
                    return;
                }

                if(!copiesField.getText().matches("\\b0*[1-9]\\d*\\b")){
                    errorLabel.setText("Please enter a valid copies");
                    errorLabel.setTextFill(Color.RED);
                    return;
                }

                Book book;
                try{
                    boolean isBookAvailable = super.getModel().getBookDao().isBookAvailable(titleField.getText());
                    if(isBookAvailable){
                        errorLabel.setText("Book Title Exist");
                        errorLabel.setTextFill(Color.RED);
                        return;
                    }
                    errorLabel.setText("");

                    book = super.getModel().getBookDao().addBook(titleField.getText(),
                            authorField.getText(),Integer.parseInt(copiesField.getText()),
                            Float.parseFloat(priceField.getText()));


                    errorLabel.setText("Book Added");
                    errorLabel.setTextFill(Color.GREEN);
                    titleField.clear();
                    authorField.clear();
                    copiesField.clear();
                    priceField.clear();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });


        back.setOnAction(event -> {
            super.navigateAdminHomePage();
        });
    }
}
