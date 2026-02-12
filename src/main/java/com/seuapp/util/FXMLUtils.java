package com.seuapp.util;
 
import com.seuapp.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
 
/**
 * Classe utilitária para carregar arquivos FXML e gerenciar cenas/palcos.
 */
public class FXMLUtils {
 
    public static void loadLoginView(Stage stage) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/fxml/LoginView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void loadMainView(Stage stage) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/fxml/MainView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Painel Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
     * NOVO MÉTODO
     * Carrega a tela de registro de novo usuário.
     * @param stage O palco (janela) atual.
     */
    public static void loadRegisterView(Stage stage) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/fxml/RegisterView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Criar Nova Conta");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 