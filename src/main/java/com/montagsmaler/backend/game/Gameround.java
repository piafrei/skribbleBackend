package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.wordsToGuess.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Gameround {
    int roundNumber;
    String drawer;
    Word activeWord;
    private List<String> rightGuessedUser = new ArrayList<String>();

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setRightGuessedUser(List<String> rightGuessedUser) {
        this.rightGuessedUser = rightGuessedUser;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public Word getActiveWord() {
        return activeWord;
    }

    public void setActiveWord(Word activeWord) {
        this.activeWord = activeWord;
    }

    public List<String> getRightGuessedUser() {
        return rightGuessedUser;
    }

    public void addRightGuessedUser(String rightGuessedUser) {
        this.rightGuessedUser.add(rightGuessedUser);
    }
}