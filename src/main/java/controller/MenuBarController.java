package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;
import java.sql.SQLException;

public class MenuBarController {
    private Model model;
    private Stage stage;
    private Stage parentStage;

    public void setMenuBarState(Model model, Stage stage, Stage parentStage) {
        this.model = model;
        this.stage = stage;
        this.parentStage = parentStage;
    }

    public void viewUserProfile(){
        this.navigateUserProfile();
    }

    public void navigateUserProfile(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserProfile.fxml"));
        UserProfileController userProfileController =  new UserProfileController(this.stage, this.model);
        loader.setController(userProfileController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userProfileController.showStage(root,"UserProfile",0,0);
        this.stage.close();
    }

    public void updateUserProfile(){
        navigateUpdateUserPage();
    }

    public void navigateUpdateUserPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateUserData.fxml"));
        UpdateUserController updateUserController =  new UpdateUserController(this.stage, this.model);
        loader.setController(updateUserController);
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        updateUserController.showStage(root,"Update User Profile",0,0);

    }

    public void logout() throws SQLException {
        this.model.setCurrentUser(null);
        this.stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));

        // Customize controller instance
        Model newModel = Model.getInstance();
        newModel.setup();
        LoginController loginController = new LoginController(new Stage(), newModel);

        loader.setController(loginController);

        GridPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loginController.showStage(root,"Welcome",500,300);

    }
}
