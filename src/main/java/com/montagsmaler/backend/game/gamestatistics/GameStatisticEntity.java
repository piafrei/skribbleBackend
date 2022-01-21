package com.montagsmaler.backend.game.gamestatistics;

import com.montagsmaler.backend.game.Gameround;
import com.montagsmaler.backend.game.wordsToGuess.Category;
import com.montagsmaler.backend.game.wordsToGuess.Difficulty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class GameStatisticEntity {
    @Id
    private String gameStatisticId;
    private UUID userID;
    private int playerGameNumber;
    private Date timePlayed;
    private int playersCount;
    private int score;
    private int rank;

    public GameStatisticEntity(UUID userID,int playerGameNumber,Date timePlayed, int playersCount, int score, int rank) {
        this.playerGameNumber = playerGameNumber;
        this.userID = userID;
        this.timePlayed = timePlayed;
        this.playersCount = playersCount;
        this.score = score;
        this.rank = rank;
    }

    public String getGameStatisticId() {
        return gameStatisticId;

    }

    public void setGameStatisticId(String gameStatisticId) {
        this.gameStatisticId = gameStatisticId;
    }

    public Date getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Date timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public int getPlayerGameNumber() {
        return playerGameNumber;
    }

    public void setPlayerGameNumber(int playerGameNumber) {
        this.playerGameNumber = playerGameNumber;
    }


    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

}
