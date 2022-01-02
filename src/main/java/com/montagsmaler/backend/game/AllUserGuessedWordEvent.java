package com.montagsmaler.backend.game;

import org.springframework.context.ApplicationEvent;

public class AllUserGuessedWordEvent extends ApplicationEvent {
    String gameId;

    public AllUserGuessedWordEvent(Object source, String gameId){
        super(source);
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}
