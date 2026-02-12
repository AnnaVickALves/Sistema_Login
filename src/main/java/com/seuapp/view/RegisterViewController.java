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
 
public class RegisterViewController {
 
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button registerButton;
    @FXML private Button backButton;
    @FXML private Label statusLabel;
 
    private final AuthController authController;
 
    public RegisterViewController() {
        this.authController = new AuthController();
    }
 
    @FXML
    private void initialize() {
        statusLabel.setText("");
    }
 
    /**
     * Manipula o evento de clique no botão de registrar.
     */
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
 
        // Validações da View
        if (username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            showStatusMessage("Todos os campos são obrigatórios.");
            return;
        }
        if (password.length() < 6) {
            showStatusMessage("A senha deve ter no mínimo 6 caracteres.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showStatusMessage("As senhas não coincidem.");
            return;
        }
 
        // Delega para o Controller de negócio
        String registrationResult = authController.registerUser(username, password);
 
        if (registrationResult.isEmpty()) { // Sucesso
            showStatusMessage("Conta criada com sucesso! Voltando para o login...");
            // Atraso para o usuário ler a mensagem antes de voltar
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                javafx.application.Platform.runLater(() -> {
                    handleBackButtonAction(null);
                });
            }).start();
        } else { // Erro
            showStatusMessage(registrationResult);
        }
    }
 
    /**
     * Manipula o evento de clique no botão de voltar.
     * Retorna para a tela de login.
     */
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        FXMLUtils.loadLoginView(currentStage);
    }
 
    /**
     * Exibe uma mensagem de status com uma animação de fade.
     * @param message A mensagem a ser exibida.
     */
    private void showStatusMessage(String message) {
        statusLabel.setText(message);
        FadeTransition ft = new FadeTransition(Duration.millis(200), statusLabel);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
}
 