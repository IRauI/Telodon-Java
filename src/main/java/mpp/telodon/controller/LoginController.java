package mpp.telodon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mpp.telodon.model.Voluntar;
import mpp.telodon.service.ServiceException;
import mpp.telodon.service.VoluntarService;

import java.util.function.Consumer;


public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorLabel;

    private Consumer<Voluntar> onLoginSuccess;

    private VoluntarService service;

    public VoluntarService getService() {
        return service;
    }

    public void setService(VoluntarService service) {
        this.service = service;
    }

    public void setOnLoginSuccess(Consumer<Voluntar> onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    @FXML
    public void handleLogin(){
        try{
            Voluntar v = service.login(username.getText(),password.getText());
            errorLabel.setText("");
            onLoginSuccess.accept(v);
        } catch (ServiceException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
