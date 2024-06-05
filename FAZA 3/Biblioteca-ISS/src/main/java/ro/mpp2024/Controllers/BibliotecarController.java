package ro.mpp2024.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ro.mpp2024.Domain.Order;
import ro.mpp2024.Domain.OrderItem;
import ro.mpp2024.Domain.Status;
import ro.mpp2024.Domain.User;
import ro.mpp2024.Service.Services;
import ro.mpp2024.Utils.MessageAlert;
import ro.mpp2024.Utils.events.BookManagementEvent;
import ro.mpp2024.Utils.events.Event;
import ro.mpp2024.Utils.events.OrderEvent;
import ro.mpp2024.Utils.observer.Observer;

import java.util.Collection;

public class BibliotecarController implements Observer<Event> {

    Stage mainStage;
    User rootUser;
    Services services;

    ObservableList<Order> modelAllOrders = FXCollections.observableArrayList();
    ObservableList<OrderItem> modelOrderItems = FXCollections.observableArrayList();

    @FXML
    TableView<Order> tableViewOrders;
    @FXML
    TableColumn<Order, Integer> tableColumnSection;
    @FXML
    TableColumn<Order, Integer> tableColumnidExemplar;
    @FXML
    TableColumn<Order, Status> tableColumnStatus;

    @FXML
    TableView<OrderItem> tableViewOrderItem;
    @FXML
    TableColumn<OrderItem, String> tableColumnOrderItemTitlu;
    @FXML
    TableColumn<OrderItem, Integer> tableColumnOrderItemidExemplar;


    public void setServices(Stage primaryStage, Services service, User rootUser){
        this.mainStage = primaryStage;
        this.services = service;
        this.rootUser = rootUser;
        service.addObserver(this);
        updateOrderList();
    }

    @FXML
    void initialize(){
        tableColumnSection.setCellValueFactory(new PropertyValueFactory<Order, Integer>("user"));
        tableColumnidExemplar.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id_exemplar"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Order, Status>("status"));
        tableViewOrders.setItems(modelAllOrders);

        tableColumnOrderItemTitlu.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("titluCarte"));
        tableColumnOrderItemidExemplar.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("id_exemplar"));
        tableViewOrderItem.setItems(modelOrderItems);

        tableViewOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null){
                Collection<OrderItem> orderItems = services.getAllOrderItemsForOrder(newVal);
                modelOrderItems.setAll(orderItems);
            }
        });

    }

    private void updateOrderList(){
        Collection<Order> orders = services.getOrdersByStatus(Status.PENDING);
        modelAllOrders.setAll(orders);
    }

    @Override
    public void update(Event event) {
        if(event instanceof BookManagementEvent || event instanceof OrderEvent)
            updateOrderList();
    }

    @FXML
    private void handleHonorOrder(){
        Order order = tableViewOrders.getSelectionModel().getSelectedItem();
        if(order == null)
            return;
        try{
            services.honorOrder(order);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Honor Order", "The order was honored!");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    private void handleRejectOrder(){
        Order order = tableViewOrders.getSelectionModel().getSelectedItem();
        if(order == null)
            return;
        try{
            services.refuseOrder(order);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Refuse Order", "The order was rejected!");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    private void handleLogout(){
        try{
            services.logout(rootUser);
            mainStage.close();
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }
}
