package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;


public class AdminHomePageController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
//    @FXML
//    private Label message;
    @FXML
    private TableView stockTable;
    @FXML
    private Button updateStock;
    @FXML
    private Button addNewBook;

    public AdminHomePageController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }



    public void showStage(Pane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Admin Home");
        stage.show();
    }

    @FXML
    public void initialize() {
        addNewBook.setOnAction(event -> {
            try{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddBook.fxml"));
                AddBookController addBookController = new AddBookController(stage, model);

                loader.setController(addBookController);
                GridPane root = loader.load();

                addBookController.showStage(root);
                stage.close();

            }catch (IOException e) {
                System.out.println(e);
            }
        });

    }
}
