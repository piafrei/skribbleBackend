package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;

public class StartGameAction extends Action {
    private int rounds;

    protected StartGameAction(ActionType actionType, int rounds) {
        super(actionType);
        this.rounds = rounds;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.startGame;
    }
}
