package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class MainController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private Label promptMessage;

    public Label getPromptMessage() {
        return promptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage.setText(promptMessage);
    }

    public MainController(Stage parentStage, Model model){
        this.model = model;
        this.stage = new Stage();
        this.parentStage = parentStage;
    }

    public void showStage(Pane root, String stageTitle, int width, int height) {
        Scene scene;
        if(width !=0 && height!=0){
            scene = new Scene(root, width, height);
        }else{
            scene = new Scene(root);
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(stageTitle);
        stage.show();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public FXMLLoader Loader (String fxmlPath){
        return new FXMLLoader(getClass().getResource(fxmlPath));
    }

    public void navigateAdminHomePage(){
        if(!this.model.getCurrentUser().isAdmin()) return;

        FXMLLoader loader = this.Loader("/view/AdminHomePage.fxml");
        AdminHomePageController adminHomePageController =
                new AdminHomePageController(this.stage, this.model);

        loader.setController(adminHomePageController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        adminHomePageController.showStage(root,"Admin Home",0,0);
        this.stage.close();
    }

    public void navigateSignUpPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignupView.fxml"));

        // Customize controller instance
        SignupController signupController =  new SignupController(this.stage, this.model);

        loader.setController(signupController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        signupController.showStage(root,"Sign up",500,500);
        this.stage.close();
    }

    public void navigateUserHomePage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));
        HomeController homeController = new HomeController(this.stage, this.model);

        loader.setController(homeController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        homeController.showStage(root,"Home",0,0);
        this.stage.close();
    }
}
