package ro.mpp2024.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import ro.mpp2024.Domain.*;
import ro.mpp2024.Service.Services;
import ro.mpp2024.Utils.MessageAlert;
import ro.mpp2024.Utils.events.BookManagementEvent;
import ro.mpp2024.Utils.events.Event;
import ro.mpp2024.Utils.observer.Observer;

import java.io.IOException;

public class ClientController implements Observer<Event> {

    Stage mainStage;
    User rootUser;
    Services services;

    ObservableList<Book> modelAllBooks = FXCollections.observableArrayList();
    ObservableList<OrderItem> modelOrderItems = FXCollections.observableArrayList();

    @FXML
    TableView<Book> tableViewBooks;
    @FXML
    TableColumn<Book, String> tableColumnTitlu;

    @FXML
    TableColumn<Book, String> tableColumnAutor;
    @FXML
    TableColumn<Book, Integer> tableColumnIdExemplar;
    @FXML
    TableColumn<Book, Boolean> tableColumnDisponibilitate;
    @FXML
    TextField idExemplarTextField;

    @FXML
    TableView<OrderItem> tableOrderItems;
    @FXML
    TableColumn<OrderItem, String> tableColumnOrderItemName;
    @FXML
    TableColumn<OrderItem, Integer> tableColumnOrderItemIdExemplar;



    public void setServices(Stage primaryStage, Services service, User rootUser){
        this.mainStage = primaryStage;
        this.services = service;
        this.rootUser = rootUser;
        service.addObserver(this);
        initModelAllBooks();
    }

    @FXML
    void initialize(){
        tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Book, String >("titlu"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Book, String>("autor"));
        tableColumnIdExemplar.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id_exemplar"));
        tableColumnDisponibilitate.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("disponibilitate"));
        tableViewBooks.setItems(modelAllBooks);

        tableColumnOrderItemName.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("titluCarte"));
        tableColumnOrderItemIdExemplar.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("id_exemplar"));
        tableOrderItems.setItems(modelOrderItems);
    }

    private void initModelAllBooks(){
        services.findAllBooks().stream().forEach(System.out::println);
        modelAllBooks.setAll(services.findAllBooks());
    }

    @Override
    public void update(Event event) {
        if(event instanceof BookManagementEvent)
            initModelAllBooks();
    }

    @FXML
    private void handleAddOrderBook(){
        Book book = tableViewBooks.getSelectionModel().getSelectedItem();
        if(book == null)
            return;
        try{
            Integer id_exemplar = Integer.valueOf(idExemplarTextField.getText());
            OrderItem orderItem = new OrderItem(new Pair<>(book.getID(), null), book.getTitlu(), id_exemplar, book, null);
            modelOrderItems.add(orderItem);
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    private void handleAddOrder(){
        Integer totalQuantity = 0;
        for(OrderItem orderItem: modelOrderItems) {
            System.out.println("LLLLLLLLLLLLLPPPPPPPPPPPPP: " + orderItem);
            totalQuantity += orderItem.getId_exemplar();
        }
        try{
            Order order = new Order(totalQuantity, rootUser, Status.PENDING);
            services.addOrder(order, modelOrderItems);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Add Order", "The order was added successfully !");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    private void handleRemoveOrderItem(){
        OrderItem orderItem = tableOrderItems.getSelectionModel().getSelectedItem();
        if(orderItem == null)
            return;
        modelOrderItems.remove(orderItem);
    }

    @FXML
    private void handleYourOrders() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ViewOrdersSection.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Scene previousScene = mainStage.getScene();
        mainStage.setScene(newScene);
        OrderSectionController orderSectionController = loader.getController();
        orderSectionController.setServices(mainStage, services, this.rootUser, previousScene);
        mainStage.show();
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
