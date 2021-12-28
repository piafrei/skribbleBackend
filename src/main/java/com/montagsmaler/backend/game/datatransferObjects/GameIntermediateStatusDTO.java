package com.montagsmaler.backend.game.datatransferObjects;

import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.canvas.CanvasDTO;

import java.util.Map;
import java.util.UUID;

public class GameIntermediateStatusDTO {
    CanvasDTO canvas;
    Map<GameUserDTO, Integer> playersToScoreMap;
    int currentRound;
    int totalRounds;
    int lengthOfActiveWord;
    String drawer;

    public GameIntermediateStatusDTO(GameEntity gameEntity, CanvasDTO canvas, Map<GameUserDTO, Integer> gameUserToScoreMap, int totalRounds) {
        this.canvas = canvas;
        this.playersToScoreMap = gameUserToScoreMap;
        if(gameEntity.isGameRunning()){
            this.currentRound = gameEntity.getActiveRound().getRoundNumber();
            this.lengthOfActiveWord = gameEntity.getActiveRound().getActiveWord().getWordLenth();
            this.drawer = gameEntity.getActiveRound().getDrawer();
        }
        this.totalRounds = totalRounds;
    }

    public CanvasDTO getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasDTO canvas) {
        this.canvas = canvas;
    }

    public void setPlayersToScoreMap(Map<GameUserDTO, Integer> playersToScoreMap) {
        this.playersToScoreMap = playersToScoreMap;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getLengthOfActiveWord() {
        return lengthOfActiveWord;
    }

    public void setLengthOfActiveWord(int lengthOfActiveWord) {
        this.lengthOfActiveWord = lengthOfActiveWord;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

}
