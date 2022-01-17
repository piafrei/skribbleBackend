package com.montagsmaler.backend.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Nutzername einen Wert an.")
    @JsonProperty("userName")
    private String username;
    @JsonProperty("password")
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Passwort einen Wert an.")
    private String passwort;
    private String avatar;

    public UserDTO(String userName, String password) {
        this.username = userName;
        this.passwort = password;
    }

    public UserDTO() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
