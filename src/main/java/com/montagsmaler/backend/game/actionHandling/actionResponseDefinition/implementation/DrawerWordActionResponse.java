package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.wordsToGuess.Word;

public class DrawerWordActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.drawerWord;

    private Word wordToDraw;

    public DrawerWordActionResponse(Word activeWord) {
        super(ACTION_RESPONSE_TYPE);
        this.wordToDraw = activeWord;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }

    public void setWordToDraw(Word wordToDraw) {
        this.wordToDraw = wordToDraw;
    }

    public Word getWordToDraw() {
        return wordToDraw;
    }
}
