package com.seuapp.model;
 
import com.seuapp.util.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class UserDAO {
 
    // ... método findUserByUsername(String username, Connection conn) permanece o mesmo ...
    public User findUserByUsername(String username, Connection conn) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("salt"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }
 
    // ... método findUserByUsername(String username) permanece o mesmo ...
    public User findUserByUsername(String username) {
        try (Connection conn = DatabaseManager.getConnection()) {
            return findUserByUsername(username, conn);
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão para buscar usuário: " + e.getMessage());
            return null;
        }
    }
 
    /**
     * Adiciona um novo usuário ao banco de dados.
     * O objeto User já deve conter o hash da senha e o salt.
     *
     * @param user O objeto User a ser inserido.
     * @throws SQLException se ocorrer um erro de banco de dados, como um username duplicado.
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO usuarios(username, password_hash, salt, role) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getSalt());
            pstmt.setString(4, user.getRole()); // Por padrão, será 'USER'
 
            pstmt.executeUpdate();
            System.out.println("Usuário '" + user.getUsername() + "' inserido com sucesso.");
        }
        // A exceção SQLException será propagada para ser tratada pelo Controller
    }
}