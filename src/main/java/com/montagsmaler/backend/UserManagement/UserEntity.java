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

    public UserEntity() {}

    public UserEntity(String userName, String password) {
        this.userID = UUID.randomUUID();
        this.password = password;
        this.userName = userName;
    }

    public UserEntity(UserDTO user){
        this.userID = UUID.randomUUID();
        this.userName = user.getBenutzername();;
        this.password = user.getPasswort();
    }

    public String getUserName() {
        return userName;
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

    public UUID getUserID() {
        return userID;
    }
}
