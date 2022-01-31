package com.montagsmaler.backend.game.gameEvents.implementation;


import com.montagsmaler.backend.game.gameEvents.GameEvent;

public class AllUserGuessedWordEvent extends GameEvent {
    public AllUserGuessedWordEvent(String gameId) {
        super(gameId);
    }
}
