package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

public class UserProfileController extends MainController{
    public UserProfileController(Stage parentStage, Model model) {
        super(parentStage, model);

    }
    @FXML
    private Label userName;

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label totalOrders;
    @FXML
    private Label totalBoughtBooks;

    @FXML
    private Button back;
    @FXML
    private Button updateProfile;

    @FXML
    public void initialize() {
        userName.setText(super.getModel().getCurrentUser().getUsername());
        firstName.setText(super.getModel().getCurrentUser().getFirstName());
        lastName.setText(super.getModel().getCurrentUser().getLastName());

        back.setOnAction(event -> {
            super.getStage().close();
            super.getParentStage().show();
        });

        updateProfile.setOnAction(event -> {
            super.navigateUpdateUserPage();
        });

    }
}
