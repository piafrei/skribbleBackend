package com.montagsmaler.backend.game.gameEvents;

public abstract class GameEvent {
    private String gameId;

    protected GameEvent(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
