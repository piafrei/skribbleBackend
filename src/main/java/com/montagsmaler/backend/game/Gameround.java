package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.wordsToGuess.Word;

import java.util.ArrayList;
import java.util.List;

public class Gameround {
    private int roundNumber;
    private String drawer;
    private Word activeWord;
    private List<String> rightGuessedUser = new ArrayList<String>();

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
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

    public void removeUser(String username){
        this.rightGuessedUser.remove(username);
    }

    public boolean userIsAllowedToGuess(String username) {
        return !this.drawer.equals(username) && !this.rightGuessedUser.contains(username);
    }
}
