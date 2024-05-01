package ro.mpp2024.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.mpp2024.Domain.TypeUser;
import ro.mpp2024.Domain.User;
import ro.mpp2024.Domain.validators.ValidationException;
import ro.mpp2024.Service.Services;

import java.io.IOException;

public class LoginController {
    Stage mainStage;
    Services services;
    TypeUser typeUser;
    TypeUser typeUserSignup;
    User rootUser;

    @FXML
    TextField loginUsernameText;
    @FXML
    TextField loginPasswordText;
    @FXML
    TextField signupUsernameText;
    @FXML
    TextField signupPasswordText;
    @FXML
    TextField signupConfirmPasswordText;
    @FXML
    Button loginBut;
    @FXML
    Button signupBut;
    @FXML
    RadioButton bibliotecarRadioLogin;
    @FXML
    RadioButton clientRadioLogin;
    @FXML
    RadioButton adminRadioLogin;
    @FXML
    RadioButton bibliotecarRadioSignup;
    @FXML
    RadioButton clientRadioSignup;
    @FXML
    RadioButton adminRadioSignup;

    public void setServices(Stage primaryStage, Services service){
        this.mainStage = primaryStage;
        this.services = service;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    void handleLogin() throws IOException {
        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        User rootUser = new User(username, password, typeUser);
        try{
            this.rootUser = services.login(rootUser);

            if(typeUser.equals(TypeUser.CLIENT)) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/Client.fxml"));
                Parent root = loader.load();
                Scene newScene = new Scene(root);
                mainStage.setScene(newScene);
                ClientController clientController = loader.getController();
                clientController.setServices(mainStage, services, this.rootUser);
                mainStage.show();
            }
            else
            if(typeUser.equals(TypeUser.BIBLIOTECAR)) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/Bibliotecar.fxml"));
                Parent root = loader.load();
                Scene newScene = new Scene(root);
                mainStage.setScene(newScene);
                BibliotecarController bibliotecarController = loader.getController();
                bibliotecarController.setServices(mainStage, services, this.rootUser);
                mainStage.show();
            }
            else
            if(typeUser.equals(TypeUser.ADMIN)) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/Admin.fxml"));
                Parent root = loader.load();
                Scene newScene = new Scene(root);
                mainStage.setScene(newScene);
                AdminController adminController = loader.getController();
                adminController.setServices(mainStage, services, this.rootUser);
                mainStage.show();
            }
        }
        catch (ValidationException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Biblioteca");
            alert.setHeaderText("Authentication failure");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            clearLogin();
        }
    }

    @FXML
    void handleSignup(){
        String username = signupUsernameText.getText();
        String password = signupPasswordText.getText();
        String confirmPassword = signupConfirmPasswordText.getText();
        if(!confirmPassword.equals(password)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Biblioteca");
            alert.setHeaderText("Authentication failure");
            alert.setContentText("Password doesn't match !");
            alert.showAndWait();
            signupUsernameText.clear();;
            signupPasswordText.clear();
            signupConfirmPasswordText.clear();
            clearSignup();
            return;
        }
        try{
            User signupUser = new User(username, password, typeUserSignup);
            services.signUp(signupUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Biblioteca");
            alert.setHeaderText("Registration");
            alert.setContentText("The user was registered !");
            alert.showAndWait();
            clearSignup();
        }catch (ValidationException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Travel Agency");
            alert.setHeaderText("Authentication failure");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            clearSignup();
        }
    }

    private void clearLogin(){
        loginUsernameText.clear();
        loginPasswordText.clear();
    }

    private void clearSignup(){
        signupUsernameText.clear();
        signupPasswordText.clear();
        signupConfirmPasswordText.clear();
    }

    @FXML
    void handleBibliotecarRadioLogin(){
        typeUser = TypeUser.BIBLIOTECAR;
    }

    @FXML
    void handleClientRadioLogin(){
        typeUser = TypeUser.CLIENT;
    }

    @FXML
    void handleAdminRadoLogin(){
        typeUser = TypeUser.ADMIN;
    }

    @FXML
    void handleBibliotecarRadioSignup(){
        typeUserSignup = TypeUser.BIBLIOTECAR;
    }

    @FXML
    void handleClientRadioSignup(){
        typeUserSignup = TypeUser.CLIENT;
    }

    @FXML
    void handleAdminRadoSignup(){
        typeUserSignup = TypeUser.ADMIN;
    }
}

