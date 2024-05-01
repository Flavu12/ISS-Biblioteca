package ro.mpp2024.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ro.mpp2024.Domain.*;
import ro.mpp2024.Utils.MessageAlert;
import ro.mpp2024.Utils.events.BookManagementEvent;
import ro.mpp2024.Utils.events.Event;
import ro.mpp2024.Utils.observer.Observer;
import ro.mpp2024.Service.Services;
public class AdminController implements Observer<Event> {

    Stage mainStage;
    User rootUser;
    Services services;

    ObservableList<Book> modelAllBooks = FXCollections.observableArrayList();

    @FXML
    TableView<Book> tableViewBooks;
    @FXML
    TableColumn<Book, String> tableColumnTitlu;
    @FXML
    TableColumn<Book, String> tableColumnAutor;

    @FXML
    TableColumn<Book, Integer> tableColumnId_exemplar;
    @FXML
    TableColumn<Book, Boolean> tableColumnDisponibilitate;
    @FXML
    TextField titluTextField;
    @FXML
    TextField autorTextField;
    @FXML
    TextField disponibilitateTextField;
    @FXML
    TextField idExemplarTextField;

    public void setServices(Stage primaryStage, Services service, User rootUser){
        this.mainStage = primaryStage;
        this.services = service;
        this.rootUser = rootUser;
        initModelAllBooks();
        service.addObserver(this);
    }

    @FXML
    void initialize(){

        tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Book, String >("titlu"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Book, String>("autor"));
        tableColumnId_exemplar.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id_exemplar"));
        tableColumnDisponibilitate.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("disponibilitate"));
        tableViewBooks.setItems(modelAllBooks);

        tableViewBooks.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null){
                titluTextField.setText(newVal.getTitlu());
                autorTextField.setText(newVal.getAutor());
                idExemplarTextField.setText((Integer.toString(newVal.getId_exemplar())));
                disponibilitateTextField.setText(newVal.getDisponibilitate().toString());
            }
        });
    }

    private void initModelAllBooks(){
        modelAllBooks.setAll(services.findAllBooks());
    }

    @Override
    public void update(Event event) {
        if(event instanceof BookManagementEvent)
            initModelAllBooks();
    }

    @FXML
    void handleAddBook(){
        try{
            String titlu = titluTextField.getText();
            String autor = autorTextField.getText();
            Integer id_exemplar = Integer.valueOf(idExemplarTextField.getText());
            Boolean disponibilitate = Boolean.valueOf(disponibilitateTextField.getText());

            Book addedBook = new Book(titlu,autor, id_exemplar, disponibilitate);
            services.addBook(addedBook);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Add Book", "The Book was added successfully !");

        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    void handleUpdateBook(){
        Book updetedBook = tableViewBooks.getSelectionModel().getSelectedItem();
        if(updetedBook == null)
            return;
        try{
            String titlu = titluTextField.getText();
            String autor = autorTextField.getText();
            Integer id_exemplar = Integer.valueOf(idExemplarTextField.getText());
            Boolean disponibilitate = Boolean.valueOf(disponibilitateTextField.getText());
            Book updatedBook = new Book(updetedBook.getID(), titlu, autor, id_exemplar, disponibilitate);
            services.updateBook(updatedBook);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Update Book", "The Book was updated successfully !");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    void handleDeleteBook(){
        Book deletedBook = tableViewBooks.getSelectionModel().getSelectedItem();
        System.out.println(deletedBook);
        if(deletedBook == null)
            return;
        try{
            services.deleteBook(deletedBook);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Delete Book", "The Book was deleted successfully !");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    void handleLogOut(){
        try{
            services.logout(rootUser);
            mainStage.close();
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

}
