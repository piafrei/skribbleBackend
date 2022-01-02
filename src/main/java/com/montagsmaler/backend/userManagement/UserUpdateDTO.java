package com.montagsmaler.backend.userManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateDTO {
    @JsonProperty("userName")
    private String benutzername;
    @JsonProperty("password")
    private String passwort;
    @JsonProperty("avatar")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}

