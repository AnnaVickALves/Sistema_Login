package com.seuapp;

import com.seuapp.util.DatabaseManager;
import com.seuapp.util.FXMLUtils;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.SQLException;

/**
 * Classe principal da aplicação. Ponto de entrada.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Inicializa o banco de dados ao iniciar a aplicação
        try {
            DatabaseManager.getConnection();
        } catch (SQLException e) {
            System.err.println("Falha crítica: Não foi possível conectar ao banco de dados. Encerrando.");
            e.printStackTrace();
            return; // Encerra a aplicação se não conseguir conectar ao DB
        }

        // Carrega a tela de login inicial
        FXMLUtils.loadLoginView(primaryStage);
    }

    @Override
    public void stop() {
        // Fecha a conexão com o banco de dados ao fechar a aplicação
        DatabaseManager.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
