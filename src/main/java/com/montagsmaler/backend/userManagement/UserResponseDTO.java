package com.montagsmaler.backend.userManagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.montagsmaler.backend.userManagement.avatar.Avatar;

import javax.validation.constraints.NotBlank;

public class UserResponseDTO {
    @NotBlank(message = "Bitte geben Sie für das Pflichtfeld Nutzername einen Wert an.")
    @JsonProperty("userName")
    private String benutzername;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    private Avatar avatar;

    public UserResponseDTO(String userName) {
        this.benutzername = userName;
    }

    public UserResponseDTO(UserEntity userEntity, Avatar avatar) {
        this.benutzername = userEntity.getUserName();
        this.avatar = avatar;
    }

    public UserResponseDTO() {
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

}
