package com.montagsmaler.backend.UserManagement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserEntity {
    @Id
    private UUID userID;
    private String userName;
    private String password;
    private int age;
    private String email;

    public UserEntity() {}

    public UserEntity(String userName, int age, String email, String password) {
        this.userID = UUID.randomUUID();
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.age = age;
    }

    public UserEntity(UserDTO user){
        this.userID = UUID.randomUUID();
        this.userName = user.getBenutzername();
        this.age = user.getAlter();
        this.email = user.getEmail().toLowerCase();
        this.password = user.getPasswort();
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
