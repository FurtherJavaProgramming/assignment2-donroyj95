package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;
import model.Order;


public class OrderSuccessfulController extends MainController{

    @FXML
    private Button continueShopping;
    @FXML
    private Label orderId;


    public OrderSuccessfulController(Stage parentStage, Model model, Order order) {
        super(parentStage, model);
        this.orderId.setText(String.valueOf(order.getId()));
    }

    public void initialize() {
        continueShopping.setOnAction(event -> {
            super.getStage().close();
            super.navigateSelectBookPage();
        });
    }
}
