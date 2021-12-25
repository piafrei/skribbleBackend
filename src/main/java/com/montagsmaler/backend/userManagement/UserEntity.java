package com.montagsmaler.backend.userManagement;

import com.montagsmaler.backend.userManagement.avatar.Avatar;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserEntity {
    @Id
    private UUID userID;
    private String userName;
    private String password;
    private Avatar avatar;

    public UserEntity() {}

    public UserEntity(String userName, String password, Avatar avatar) {
        this.userID = UUID.randomUUID();
        this.password = password;
        this.userName = userName;
        this.avatar = avatar;
    }

    public UserEntity(UserDTO user){
        this.userID = UUID.randomUUID();
        this.userName = user.getBenutzername();;
        this.password = user.getPasswort();
        initialiseAvatar(user);
    }

    private void initialiseAvatar(UserDTO user) {
        if(user.getAvatar() != null){
            this.avatar = user.getAvatar();
        } else {
            Avatar avatar = new Avatar();
            avatar.setToDefaults();
            this.avatar = avatar;
        }
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
