package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Order;
import model.OrderView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private Label promptMessage;

    public MainController() {}

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
        HomeController homeController = new HomeController(this.parentStage, this.model);

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
//        this.stage.close();
    }

//    @FXML
//    public void handleOpenAction(javafx.event.ActionEvent actionEvent) {
//        this.navigateUserProfile();
//    }

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

    public void navigateSelectBookPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelectBooks.fxml"));
        SelectBooksController selectBooksController =  new SelectBooksController(this.stage, this.model);
        loader.setController(selectBooksController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        selectBooksController.showStage(root,"Select Books",0,0);

    }

    public void navigateShoppingCartPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShoppingCart.fxml"));
        ShoppingCartController shoppingCartController =  new ShoppingCartController(this.stage, this.model);
        loader.setController(shoppingCartController);
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        shoppingCartController.showStage(root,"Update User Profile",0,0);

    }
    public void navigateCheckoutPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Checkout.fxml"));
        CheckoutController checkoutController =  new CheckoutController(this.stage, this.model);
        loader.setController(checkoutController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        checkoutController.showStage(root,"Checkout",0,0);

    }

    public void navigateOrderSuccessfulPage(Order order){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderSuccessful.fxml"));
        OrderSuccessfulController orderSuccessfulController =  new OrderSuccessfulController(this.stage, this.model,order);
        loader.setController(orderSuccessfulController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        orderSuccessfulController.showStage(root,"Checkout",0,0);
    }

    public void backButtonAction(){
        this.getStage().close();
        this.getParentStage().show();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void logout() {
        this.model.setCurrentUser(null);
        this.model.setShoppingCart(null);
        this.stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));

        // Customize controller instance
        Model newModel = new Model();
        try {
            newModel.setup();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void navigateViewAllOrders(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewAllOrders.fxml"));
        ViewAllOrdersController viewAllOrdersController =  new ViewAllOrdersController(this.stage, this.model);
        loader.setController(viewAllOrdersController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        viewAllOrdersController.showStage(root,"All Orders",0,0);
    }



    public void navigateExportOrders(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportOrders.fxml"));
        ExportOrdersController exportOrdersController =  new ExportOrdersController(this.stage, this.model);
        loader.setController(exportOrdersController);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        exportOrdersController.showStage(root,"Export Selected Orders",0,0);
    }

}
