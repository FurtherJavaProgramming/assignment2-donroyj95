package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class LoginController extends MainController{
	@FXML
    public TextField name;
	@FXML
    public PasswordField password;
	@FXML
    public Label message;
	@FXML
    public Button login;
	@FXML
    public Button signup;
	private Main main;


	public LoginController(Stage primaryStage, Model model) {
		super(primaryStage,model);

	}


	@FXML
	public void initialize() {		
		login.setOnAction(event -> {
			if (!isTextFieldEmpty(name) && !isTextFieldEmpty(password)) {
				User user;
				try {
					user = super.getModel().getUserDao().getUser(name.getText(), password.getText());
					if (user != null) {
						super.getModel().setCurrentUser(user);
						if(user.isAdmin()){
							super.navigateAdminHomePage();

						}else{
							super.navigateUserHomePage();
						}

						
					} else {
						displayErrorMessage(message,"Wrong username or password");
					}
				} catch (SQLException e) {
					displayErrorMessage(message,e.getMessage());
				}
				
			} else {
				displayErrorMessage(message,"Empty username or password");
			}
			name.clear();
			password.clear();
		});
		
		signup.setOnAction(event -> {
				super.navigateSignUpPage();
				message.setText("");
				name.clear();
				password.clear();
			});
	}
}

