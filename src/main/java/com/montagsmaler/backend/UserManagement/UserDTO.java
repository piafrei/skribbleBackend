package com.montagsmaler.backend.UserManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Nutzername einen Wert an.")
    @JsonProperty("userName")
    private String benutzername;
    @JsonProperty("password")
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Passwort einen Wert an.")
    private String passwort;
    private Avatar avatar;

    public UserDTO(String userName, String password) {
        this.benutzername = userName;
        this.passwort = password;
    }

    public UserDTO(UserEntity userEntity) {
        this.benutzername = userEntity.getUserName();
        this.avatar = userEntity.getAvatar();
    }

    public UserDTO() {
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
