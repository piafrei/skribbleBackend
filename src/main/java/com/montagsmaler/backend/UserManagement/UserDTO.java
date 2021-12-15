package com.montagsmaler.backend.UserManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Nutzername einen Wert an.")
    @JsonProperty("userName")
    private String benutzername;
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Email-Adresse einen Wert an.")
    @Email(message = "Bitte wählen Sie eine valide Email-Adresse")
    @JsonProperty("email")
    private String email;
    @NotNull(message = "Bitte geben Sie für das Pflichtfeld Alter einen Wert an.")
    @JsonProperty("age")
    private Integer alter;
    @JsonProperty("password")
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Passwort einen Wert an.")
    private String passwort;

    public UserDTO(String userName, String email, Integer age, String password) {
        this.benutzername = userName;
        this.email = email;
        this.alter = age;
        this.passwort = password;
    }

    public UserDTO(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.benutzername = userEntity.getUserName();
        this.alter = userEntity.getAge();
    }

    public UserDTO() {
    }

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
