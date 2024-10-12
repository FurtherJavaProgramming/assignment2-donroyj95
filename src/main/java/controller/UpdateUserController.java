package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

import java.sql.SQLException;


public class UpdateUserController extends MainController{

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;
    @FXML
    private Button updateProfile;
    @FXML
    private Button back;

    public UpdateUserController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    @FXML
    public void initialize() {
        User user = super.getModel().getCurrentUser();
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        password.setText(user.getPassword());

        back.setOnAction(event -> {
            super.getStage().close();
            super.getParentStage().show();
        });

        updateProfile.setOnAction(event -> {
            if(!firstName.getText().isEmpty()&&!lastName.getText().isEmpty()&&!password.getText().isEmpty()) {
                user.setFirstName(firstName.getText());
                user.setLastName(lastName.getText());
                user.setPassword(password.getText());
                super.getModel().setCurrentUser(user);
                try {
                    super.getModel().getUserDao().updateUser(user);
                    super.getPromptMessage().setText("Profile updated");
                    super.getPromptMessage().setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                super.getPromptMessage().setText("Fill all fields");
                super.getPromptMessage().setTextFill(Color.RED);
            }

        });

    }
}
