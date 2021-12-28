package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;

public class ChatAction extends Action {
    private String message;

    public ChatAction(ActionType actionType, String message) {
        super(actionType);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.chat;
    }
}
