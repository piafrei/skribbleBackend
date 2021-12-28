package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;

public class AddUserToGameAction extends Action {
    protected AddUserToGameAction() {
        super(ActionType.addUserToGame);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.addUserToGame;
    }
}
