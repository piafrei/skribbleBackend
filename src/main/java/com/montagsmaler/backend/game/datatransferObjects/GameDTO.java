package com.montagsmaler.backend.game.datatransferObjects;

import com.montagsmaler.backend.game.wordsToGuess.Category;
import com.montagsmaler.backend.game.wordsToGuess.Difficulty;

public class GameDTO {
    private Difficulty difficulty;
    private Category category;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
