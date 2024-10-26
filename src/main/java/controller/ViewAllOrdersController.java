package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.OrderView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllOrdersController extends MainController{

    @FXML
    private TableView<OrderView> viewAllOrdersTable;

    @FXML
    public TableColumn<OrderView, Integer> orderId;
    @FXML
    public TableColumn<OrderView, String> dateTime;
    @FXML
    public TableColumn<OrderView, Float> totalPrice;
    @FXML
    public TableColumn<OrderView, String> booksAndCopies;

    @FXML
    public Button exportToCsv;
    @FXML
    public Button back;

    private ArrayList<OrderView> orderViews = new ArrayList<>();

    public ViewAllOrdersController(Stage parentStage, Model model) {
        super(parentStage, model);
    }

    public void initialize() {

        try {
            System.out.println(this.getModel().getCurrentUser().getUsername());
            orderViews = this.getModel().getBookOrderDao().getBooksOrdersByUsername(
                    this.getModel().getCurrentUser().getUsername());
            System.out.println(orderViews.get(0).getOrderId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableColumn<OrderView, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().isSelectedProperty());
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        viewAllOrdersTable.getColumns().addFirst(selectColumn);

        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        booksAndCopies.setCellValueFactory(new PropertyValueFactory<>("booksAndCopies"));

        viewAllOrdersTable.setItems(FXCollections.observableArrayList(orderViews));

        back.setOnAction(event -> {
            super.getStage().close();
            super.getParentStage().show();
        });

        exportToCsv.setOnAction(event -> {
            ArrayList<OrderView> selectedOrders = new ArrayList<>();
            for(OrderView orderView :orderViews){
                if(orderView.isSelected()){
                    selectedOrders.add(orderView);
                }
            }

            if(selectedOrders.isEmpty()) {
                super.setPromptMessage("Please select an order");
                super.getPromptMessage().setTextFill(Color.RED);
                return;
            }
            super.setPromptMessage("");

            super.getModel().setSelectedOrdersToExport(selectedOrders);
            super.getStage().close();
            super.navigateExportOrders();
        });

    }

}
