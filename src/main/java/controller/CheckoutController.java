package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Order;

import java.sql.Date;
import java.sql.SQLException;

public class CheckoutController extends  MainController{
    @FXML
    private Label totalPriceLabel;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expiryDateField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button proceedPayment;
    @FXML
    private Button back;

    public CheckoutController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    public void initialize() {
        String regex = "^(0[1-9]|1[0-2])/(19|20)\\d{2}$";

        totalPriceLabel.setText("Total Price: "+this.getModel().getShoppingCart().getTotalPrice());


        proceedPayment.setOnAction(event -> {

            if(cardNumberField.getText().isEmpty() || cardNumberField.getText().length() != 5 || !isInteger(cardNumberField.getText())){
                super.setPromptMessage("Please enter a valid card number");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }

            if(expiryDateField.getText().isEmpty() || ! expiryDateField.getText().matches(regex)){
                super.setPromptMessage("Please enter a valid expiry date");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }

            if(cvvField.getText().isEmpty() || cvvField.getText().length() != 3){
                super.setPromptMessage("Please enter a valid cvv");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }

            try {
                this.getModel().getBookDao().updateSoldCopies(this.getModel().getShoppingCart().getShoppingCartBooks());
                Order order = this.getModel().getOrderDao().addOrder(this.getModel().getCurrentUser().getUsername(),
                        this.getModel().getShoppingCart().getTotalPrice());
                this.getModel().getBookOrderDao().addBooksOrder(order.getId(),
                        this.getModel().getShoppingCart().getShoppingCartBooks());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            super.setPromptMessage("");
            super.getStage().close();
            super.navigateOrderSuccessfulPage(order);

        });

        back.setOnAction(event -> {
            super.getStage().close();
            super.getParentStage().show();
        });
    }

}
