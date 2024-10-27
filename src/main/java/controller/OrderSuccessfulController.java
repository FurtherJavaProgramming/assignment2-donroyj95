package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;
import model.Order;
import model.ShoppingCart;


public class OrderSuccessfulController extends MainController{

    @FXML
    private Button continueShopping;
    @FXML
    private Button exit;
    @FXML
    private Label orderId;

    private Order order;


    public OrderSuccessfulController(Stage parentStage, Model model, Order order) {
        super(parentStage, model);
        this.order = order;
    }

    public void initialize() {
        this.orderId.setText(String.valueOf(order.getId()));
        continueShopping.setOnAction(event -> {
            super.getModel().setShoppingCart(new ShoppingCart());
            super.getStage().close();
            super.navigateSelectBookPage();
        });

        exit.setOnAction(event -> {
            super.logout();
        });

    }
}
