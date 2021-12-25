package com.montagsmaler.backend.actionHandling.actionInput;

public class StartGameAction extends Action {
    private int timePerRound;

    protected StartGameAction(ActionType actionType, int timePerRound) {
        super(actionType);
        this.timePerRound = timePerRound;
    }

    public int getTimePerRound() {
        return timePerRound;
    }

    public void setTimePerRound(int timePerRound) {
        this.timePerRound = timePerRound;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.startGame;
    }
}
