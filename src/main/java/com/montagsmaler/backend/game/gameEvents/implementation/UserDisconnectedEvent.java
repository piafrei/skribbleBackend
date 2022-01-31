package com.montagsmaler.backend.game.gameEvents.implementation;

import com.montagsmaler.backend.game.gameEvents.GameEvent;

public class UserDisconnectedEvent extends GameEvent {
    private String userId;
    private String host;

    public UserDisconnectedEvent(String gameId, String userId, String host) {
        super(gameId);
        this.userId = userId;
        this.host = host;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
