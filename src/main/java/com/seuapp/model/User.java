package com.seuapp.model;
import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String passwordHash; //senhacrip
    private String salt; //temperosenha
    private String role; //cargo
    private Timestamp createdAt; //horario de criacao do usuario


    //Construtor da classe User
    public User (int id, String username, String passwordHash, String salt,  String role, Timestamp createdAt){
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt; 
        this.role = role;
        this.createdAt = createdAt;
    }

    public User (){

    }

    //Getters e Setters ID

    //Get - pega o valor e retorna
    public int getId(){
        return this.id;
    }
    //Set - pega o valor e modifica
    public void setId(int id){
        this.id = id;
    }

    //Getters e Setters UserName
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    //Getters e Setters Password_hash
    public String getPasswordHash(){
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }


    //Getters e Setters Salt
    public String getSalt(){
        return this.salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }


    //Getters e Setters Role
    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }


    //Getters e Setters Created_at
    public Timestamp getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }

    //Metodo de Sobrescrita de Apresentação
    @Override
    public String toString(){
        return "ID:" + id + ", usúario: " + username + ", role = " + role ; 
    }
}
