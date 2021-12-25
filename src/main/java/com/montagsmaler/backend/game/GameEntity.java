package com.montagsmaler.backend.game;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class GameEntity {
    @Id
    private String gameId;
    private Canvas canvas;
    private Map<UUID,Integer> playerToScoreMap = new HashMap<>();
    private UUID host;
    private int rounds;
    private int activeRound;
    private Gameround gameround;

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getActiveRound() {
        return activeRound;
    }

    public void setActiveRound(int activeRound) {
        this.activeRound = activeRound;
    }

    public Gameround getGameround() {
        return gameround;
    }

    public void setGameround(Gameround gameround) {
        this.gameround = gameround;
    }


    public Set<UUID> getPlayers(){
        return playerToScoreMap.keySet();
    }

    public void addPlayer(UUID userId){
        playerToScoreMap.put(userId, 0);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Map<UUID, Integer> getPlayerToScoreMap() {
        return playerToScoreMap;
    }

    public void setPlayerToScoreMap(Map<UUID, Integer> playerToScoreMap) {
        this.playerToScoreMap = playerToScoreMap;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public UUID getHost() {
        return host;
    }

    public void setHost(UUID host) {
        this.host = host;
    }
}
