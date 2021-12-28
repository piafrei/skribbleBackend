package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;

public class StartGameActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.startGame;

    public StartGameActionResponse() {
        super(ACTION_RESPONSE_TYPE);
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }
}
