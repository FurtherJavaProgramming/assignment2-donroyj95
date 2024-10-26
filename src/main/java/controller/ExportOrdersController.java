package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Model;

import java.awt.*;

public class ExportOrdersController extends MainController{

    @FXML
    private TextField fileNameField;
    @FXML
    private TextField destinationField;
    @FXML
    private Button browse;
    @FXML
    private Button exportOrders;
    @FXML
    private Button back;


    public ExportOrdersController(Stage parentStage, Model model) {
        super(parentStage, model);
    }


}
