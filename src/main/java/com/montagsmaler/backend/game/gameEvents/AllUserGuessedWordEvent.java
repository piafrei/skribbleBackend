package com.montagsmaler.backend.game.gameEvents;


public class AllUserGuessedWordEvent extends GameEvent {
    public AllUserGuessedWordEvent(String gameId) {
        super(gameId);
    }
}
