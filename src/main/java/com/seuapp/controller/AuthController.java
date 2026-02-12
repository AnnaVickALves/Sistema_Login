package com.seuapp.controller;
 
import com.seuapp.model.User;
import com.seuapp.model.UserDAO;
import com.seuapp.util.PasswordUtils;
import com.seuapp.util.SessionManager;
import java.sql.SQLException;
 
public class AuthController {
 
    private final UserDAO userDAO;
 
    public AuthController() {
        this.userDAO = new UserDAO();
    }
 
    // ... método login(String username, String password) permanece o mesmo ...
    public boolean login(String username, String password) {
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        if (PasswordUtils.verifyPassword(password, user.getSalt(), user.getPasswordHash())) {
            SessionManager.getInstance().setCurrentUser(user);
            System.out.println("Login bem-sucedido para o usuário: " + user.getUsername());
            return true;
        }
        return false;
    }
 
    // ... método logout() permanece o mesmo ...
    public void logout() {
        SessionManager.getInstance().clearSession();
        System.out.println("Usuário deslogado com sucesso.");
    }
 
    /**
     * Registra um novo usuário no sistema.
     *
     * @param username O nome de usuário desejado.
     * @param password A senha em texto plano.
     * @return Uma string vazia em caso de sucesso, ou uma mensagem de erro caso contrário.
     */
    public String registerUser(String username, String password) {
        if (userDAO.findUserByUsername(username) != null) {
            return "Erro: Nome de usuário já existe.";
        }
 
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
 
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashedPassword);
        newUser.setSalt(salt);
        newUser.setRole("USER"); // Novos usuários são sempre do tipo 'USER'
 
        try {
            userDAO.addUser(newUser);
            return ""; // Retorna string vazia para indicar sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro no banco de dados ao tentar criar o usuário.";
        }
    }
}