package controller;

import java.io.IOException;
import java.sql.SQLException;

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
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button login;
	@FXML
	private Button signup;

	
	public LoginController(Stage stage, Model model) {
        super(stage,model);

    }


	
	@FXML
	public void initialize() {		
		login.setOnAction(event -> {
			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
					user = super.getModel().getUserDao().getUser(name.getText(), password.getText());
					if (user != null) {
						super.getModel().setCurrentUser(user);
						System.out.println(super.getModel().getCurrentUser().getFirstName());
						if(user.isAdmin()){
							super.navigateAdminHomePage();

						}else{
							super.navigateUserHomePage();
						}

						
					} else {
						message.setText("Wrong username or password");
						message.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					message.setText(e.getMessage());
					message.setTextFill(Color.RED);
				}
				
			} else {
				message.setText("Empty username or password");
				message.setTextFill(Color.RED);
			}
			name.clear();
			password.clear();
		});
		
		signup.setOnAction(event -> {
				super.navigateSignUpPage();
				message.setText("");
				name.clear();
				password.clear();
//				super.getStage().close();
			});
	}
}

