package com.seuapp.view;
 
import com.seuapp.controller.AuthController;
import com.seuapp.util.FXMLUtils;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class LoginViewController {
 
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button clearButton;
    @FXML private Label statusLabel;
 
    // NOVA REFERÊNCIA
    @FXML private Button createAccountButton;
 
    private final AuthController authController;
 
    public LoginViewController() {
        this.authController = new AuthController();
    }
 
    // ... métodos initialize(), handleLoginButtonAction(), handleClearButtonAction(), showStatusMessage() permanecem os mesmos ...
    @FXML
    private void initialize() {
        statusLabel.setText("");
    }
 
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
 
        if (username.isBlank() || password.isBlank()) {
            showStatusMessage("Usuário e senha não podem estar vazios.");
            return;
        }
 
        boolean isAuthenticated = authController.login(username, password);
 
        if (isAuthenticated) {
            showStatusMessage("Login bem-sucedido!");
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            FXMLUtils.loadMainView(currentStage);
        } else {
            showStatusMessage("Usuário ou senha inválidos.");
            passwordField.clear();
        }
    }
 
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        usernameField.clear();
        passwordField.clear();
        statusLabel.setText("");
    }
 
    private void showStatusMessage(String message) {
        statusLabel.setText(message);
        FadeTransition ft = new FadeTransition(Duration.millis(200), statusLabel);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
 
    /**
     * NOVO MÉTODO
     * Manipula o evento de clique no botão de criar conta.
     * Abre a janela de registro.
     */
    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) createAccountButton.getScene().getWindow();
        FXMLUtils.loadRegisterView(currentStage);
    }
}