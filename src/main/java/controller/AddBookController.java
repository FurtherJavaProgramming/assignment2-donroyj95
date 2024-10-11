package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Model;

import java.sql.SQLException;


public class AddBookController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField copiesField;
    @FXML
    private Button cancel;
    @FXML
    private Button addBook;
    @FXML
    private Label errorLabel;

    public AddBookController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }


    public void showStage(Pane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add New Book");
        stage.show();
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
                    boolean isBookAvailable = model.getBookDao().isBookAvailable(titleField.getText());
                    if(isBookAvailable){
                        errorLabel.setText("Book Title Exist");
                        errorLabel.setTextFill(Color.RED);
                        return;
                    }
                    errorLabel.setText("");

                    book = model.getBookDao().addBook(titleField.getText(),
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


        cancel.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
    }
}
