package com.montagsmaler.backend.userManagement;

import com.montagsmaler.backend.userManagement.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserEntity {
    @Id
    private UUID userID;
    private String userName;
    private String password;
    private String avatar;

    public UserEntity() {}

    public UserEntity(String userName, String password, String avatar) {
        this.userID = UUID.randomUUID();
        this.password = password;
        this.userName = userName;
        this.avatar = avatar;
    }

    public UserEntity(UserDTO user){
        this.userID = UUID.randomUUID();
        this.userName = user.getUsername();;
        this.password = user.getPasswort();
        this.avatar = user.getAvatar();
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
