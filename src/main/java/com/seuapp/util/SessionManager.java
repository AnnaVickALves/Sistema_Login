package com.seuapp.util;
import com.seuapp.model.User;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;

    //Gerenciador de sessão singleton
    public static SessionManager getInstance(){
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }


    //Definir o usuário autal: set
    public void setCurrentUser(User user){
        this.currentUser = user;
    }


    //Obter o usuário atual: get
    public User getCurrentUser(){
        return currentUser;
    }


    //Limpar a sessão do usuário atual
    public void clearSession(){
        this.currentUser = null;
    }


    //Verificar se um usuário está logado
    public boolean isUserLoggedIn(){
        return this.currentUser != null;
    }


    //Verificar a permissao do usuário atual 
    public boolean hasPermission(String requiredRole){
        //Verificar se o usuário do está logado
        if (!isUserLoggedIn()) {
           return false;
        }

        //Verificar se o papel do usuário é ADMIn
        if (currentUser.getRole().equals("ADMIN")){
            return true;
        }

        //Verificar se o papel do usuário corresponde ao papel necessário
        return currentUser.getRole().equals(requiredRole);
    }
}
