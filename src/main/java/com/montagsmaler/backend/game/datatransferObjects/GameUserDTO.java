package com.montagsmaler.backend.game.datatransferObjects;

import com.montagsmaler.backend.userManagement.UserEntity;
import com.montagsmaler.backend.userManagement.avatar.Avatar;

public class GameUserDTO {
    private String benutzername;
    private Avatar avatar;

    public GameUserDTO(UserEntity userEntity) {
        this.benutzername = userEntity.getUserName();
        this.avatar = userEntity.getAvatar();
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
