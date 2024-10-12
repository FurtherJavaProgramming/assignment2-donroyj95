package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class MenuBarController {
    private Model model;
    private Stage stage;
    private Stage parentStage;

    public void setMenuBarState(Model model, Stage stage, Stage parentStage) {
        this.model = model;
        this.stage = stage;
        this.parentStage = parentStage;
    }

    public void handleOpenAction(){
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
}
