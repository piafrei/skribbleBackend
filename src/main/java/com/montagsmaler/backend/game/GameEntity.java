package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.wordsToGuess.Category;
import com.montagsmaler.backend.game.wordsToGuess.Difficulty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Document
public class GameEntity {
    private static final int DEFAULT_ROUNDS_PER_GAME = 3;

    @Id
    private String gameId;
    private Map<String,Integer> playerToOverallScoreMap = new HashMap<>();
    private String host;
    private int rounds = DEFAULT_ROUNDS_PER_GAME;
    private Gameround activeRound;

    public GameEntity() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Category category;
    private Difficulty difficulty;

    public int getRounds() {
        return rounds;
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

    public void setPlayerToOverallScoreMap(Map<String, Integer> newPlayerToOverallScoreMap) {
        this.playerToOverallScoreMap.clear();
        this.playerToOverallScoreMap = newPlayerToOverallScoreMap;
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

    public String removePlayerFromGame(String username){
        playerToOverallScoreMap.remove(username);
        if(activeRound != null){
            activeRound.removeUser(username);
        }
        if (username.equals(host) && !CollectionUtils.isEmpty(getPlayers())){
            host = getPlayers().iterator().next();
        }
        return host;
    }
}
