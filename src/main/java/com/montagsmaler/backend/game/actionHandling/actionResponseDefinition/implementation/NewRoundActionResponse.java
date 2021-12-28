package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;

public class NewRoundActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.newRound;

    private GameUserDTO drawer;
    private int lengthOfWord;
    private int roundNumber;

    public NewRoundActionResponse(GameUserDTO drawer, int lengthOfWord, int roundNumber) {
        super(ACTION_RESPONSE_TYPE);
        this.drawer = drawer;
        this.lengthOfWord = lengthOfWord;
        this.roundNumber = roundNumber;
    }

    public GameUserDTO getDrawer() {
        return drawer;
    }

    public void setDrawer(GameUserDTO drawer) {
        this.drawer = drawer;
    }

    public int getLengthOfWord() {
        return lengthOfWord;
    }

    public void setLengthOfWord(int lengthOfWord) {
        this.lengthOfWord = lengthOfWord;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
