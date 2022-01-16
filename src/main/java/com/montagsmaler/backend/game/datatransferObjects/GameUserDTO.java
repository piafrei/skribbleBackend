package com.montagsmaler.backend.game.datatransferObjects;

import com.montagsmaler.backend.userManagement.UserEntity;
import com.montagsmaler.backend.userManagement.avatar.Avatar;

public class GameUserDTO {
    private String username;
    private Avatar avatar;
    private Boolean isHost;

    public GameUserDTO(UserEntity userEntity, Avatar avatar, Boolean isHost) {
        this.username = userEntity.getUserName();
        this.avatar = avatar;
        this.isHost = isHost;
    }

    public GameUserDTO(UserEntity userEntity, Avatar avatar) {
        this.username = userEntity.getUserName();
        this.avatar = avatar;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }
}
