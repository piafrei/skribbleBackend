package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.wordsToGuess.Category;
import com.montagsmaler.backend.game.wordsToGuess.Difficulty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class GameEntity {
    @Id
    private String gameId;
    private Map<String,Integer> playerToOverallScoreMap = new HashMap<>();
    private String host;
    private int rounds = 3;
    private Gameround activeRound;

    public GameEntity() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    private Category category;
    private Difficulty difficulty;

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Gameround getActiveRound() {
        return activeRound;
    }

    public void setActiveRound(Gameround activeRound) {
        this.activeRound = activeRound;
    }

    public Set<String> getPlayers(){
        return playerToOverallScoreMap.keySet();
    }

    public void addPlayer(String userId){
        playerToOverallScoreMap.put(userId, 0);
    }

    public Map<String, Integer> getPlayerToOverallScoreMap() {
        return playerToOverallScoreMap;
    }

    public void setPlayerToOverallScoreMap(Map<String, Integer> playerToOverallScoreMap) {
        this.playerToOverallScoreMap = playerToOverallScoreMap;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isGameRunning(){
        return activeRound != null;
    }
}
