package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class HomeController extends MainController {

	@FXML
	private MenuItem viewProfile;
	@FXML
	private MenuItem updateProfile;
	
	public HomeController(Stage parentStage, Model model) {
		super(parentStage, model);
	}
}
