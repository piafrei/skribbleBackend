package com.montagsmaler.backend.game.gameEvents;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventHandler {

    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyUpdate(GameEvent gameEvent) {
        for(Observer observer: observers) {
            if(gameEvent.getGameId().equals(observer.getGameObserverIdentifier())){
                observer.update(gameEvent);
            }
        }
    }
}
