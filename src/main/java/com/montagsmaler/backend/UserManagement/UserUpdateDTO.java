package com.montagsmaler.backend.UserManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;

public class UserUpdateDTO {
    @JsonProperty("userName")
    private String benutzername;
    @Email(message = "Bitte wählen Sie eine valide Email-Adresse")
    @JsonProperty("email")
    private String email;
    @JsonProperty("age")
    private Integer alter;
    @JsonProperty("password")
    private String passwort;

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAlter() {
        return alter;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}

