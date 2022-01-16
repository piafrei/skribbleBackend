package com.montagsmaler.backend.game.gameEvents;

public interface Observer
{
    void update(GameEvent m);
    String getGameObserverIdentifier();
}