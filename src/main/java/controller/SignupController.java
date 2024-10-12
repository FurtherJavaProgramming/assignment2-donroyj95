package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class SignupController extends MainController {
	@FXML
	private TextField username;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField password;
	@FXML
	private Button createUser;
	@FXML
	private Button close;
	@FXML
	private Label status;
	
//	private Stage stage;
//	private Stage parentStage;
//	private Model model;
	
	public SignupController(Stage parentStage, Model model) {
		super(parentStage, model);
	}

	@FXML
	public void initialize() {
		createUser.setOnAction(event -> {
			if(username.getText().isEmpty()){
				status.setText("Empty username");
				status.setTextFill(Color.RED);
				return;
			}else if(firstName.getText().isEmpty()){
				status.setText("Empty first name");
				status.setTextFill(Color.RED);
				return;
			}else if(lastName.getText().isEmpty()){
				status.setText("Empty last name");
				status.setTextFill(Color.RED);
				return;
			}else if(password.getText().isEmpty()){
				status.setText("Empty password");
				status.setTextFill(Color.RED);
				return;
			}
			status.setText("");

				User user;
				try {
					Boolean isUserNameExist = super.getModel().getUserDao().isUserNameExist(username.getText().toLowerCase());

					if(isUserNameExist){
						status.setText("Username already exists");
						status.setTextFill(Color.RED);
						return;
					}

					user = super.getModel().getUserDao().createUser(username.getText().toLowerCase(), password.getText(),
							firstName.getText(), lastName.getText(),false);
					if (user != null) {
						status.setText("Created " + user.getUsername());
						status.setTextFill(Color.GREEN);

					} else {
						status.setText("Cannot create user");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText(e.getMessage());
					status.setTextFill(Color.RED);
				}
		});

		close.setOnAction(event -> {
			super.getStage().close();
			super.getParentStage().show();
		});
	}
	
//	public void showStage(Pane root) {
//		Scene scene = new Scene(root, 500, 500);
//		stage.setScene(scene);
//		stage.setResizable(false);
//		stage.setTitle("Sign up");
//		stage.show();
//	}
}
