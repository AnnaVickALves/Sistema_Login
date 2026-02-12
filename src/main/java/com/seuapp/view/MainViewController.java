package com.seuapp.view;

import com.seuapp.model.User;
import com.seuapp.util.FXMLUtils;
import com.seuapp.util.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller para a tela principal (MainView.fxml).
 */
public class MainViewController {

    @FXML private Label welcomeLabel;
    @FXML private Label roleLabel;
    @FXML private Button adminButton;
    @FXML private Button logoutButton;

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            welcomeLabel.setText("Bem-vindo, " + currentUser.getUsername() + "!");
            roleLabel.setText("Seu nível de acesso é: " + currentUser.getRole());

            // Controle de permissão: mostra o botão de admin apenas para usuários ADMIN
            if (SessionManager.getInstance().hasPermission("ADMIN")) {
                adminButton.setVisible(true);
            }
        }
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        SessionManager.getInstance().clearSession();
        // Fecha a janela principal e volta para a tela de login
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        FXMLUtils.loadLoginView(currentStage);
    }

    @FXML
    private void handleAdminAction(ActionEvent event) {
        // Lógica para abrir o painel de administração
        System.out.println("Acesso ao painel de administração concedido.");
        // Aqui você poderia carregar outra tela FXML para o painel de admin
    }
}
