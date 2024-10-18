package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;


public class OrderSuccessfulController extends MainController{

    @FXML
    private Button continueShopping;


    public OrderSuccessfulController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    public void initialize() {
        continueShopping.setOnAction(event -> {
            super.getStage().close();
            super.navigateSelectBookPage();
        });
    }
}
