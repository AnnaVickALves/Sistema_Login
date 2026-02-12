package com.seuapp.util;

import com.seuapp.model.UserDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:app_database.db";
    private static Connection connection;

    private DatabaseManager() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Conexão com o SQLite estabelecida.");
                // Chamamos a inicialização APENAS quando uma nova conexão é criada.
                initializeDatabase(connection); 
            } catch (ClassNotFoundException e) {
                System.err.println("Driver SQLite não encontrado.");
                e.printStackTrace();
                throw new SQLException("Driver SQLite não encontrado.", e);
            }
        }
        return connection;
    }

    /**
     * Inicializa o banco de dados usando uma conexão fornecida.
     * @param conn A conexão ativa a ser usada.
     */
    private static void initializeDatabase(Connection conn) {
        // Agora usamos a conexão que já está aberta e garantida
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "username VARCHAR(50) UNIQUE NOT NULL," +
                         "password_hash VARCHAR(255) NOT NULL," +
                         "salt VARCHAR(255) NOT NULL," +
                         "role TEXT CHECK(role IN ('ADMIN', 'USER')) NOT NULL DEFAULT 'USER'," +
                         "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            stmt.execute(sql);
            System.out.println("Tabela 'usuarios' verificada/criada com sucesso.");

            UserDAO userDAO = new UserDAO();
            // Usamos o método que recebe a conexão, evitando que ela seja fechada.
            if (userDAO.findUserByUsername("admin", conn) == null) {
                String salt = PasswordUtils.generateSalt();
                String hashedPassword = PasswordUtils.hashPassword("admin", salt);

                String insertAdminSQL = "INSERT INTO usuarios (username, password_hash, salt, role) VALUES ('admin', '" +
                                        hashedPassword + "', '" + salt + "', 'ADMIN')";
                stmt.execute(insertAdminSQL);
                System.out.println("Usuário 'admin' padrão criado com sucesso.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados.");
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o SQLite fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados.");
                e.printStackTrace();
            }
        }
    }
}
