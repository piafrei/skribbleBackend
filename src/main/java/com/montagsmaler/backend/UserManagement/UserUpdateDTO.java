package com.montagsmaler.backend.UserManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateDTO {
    @JsonProperty("userName")
    private String benutzername;
    @JsonProperty("password")
    private String passwort;
    @JsonProperty("avatar")
    private Avatar avatar;

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

