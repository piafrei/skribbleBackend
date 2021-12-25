package com.montagsmaler.backend.actionHandling.actionInput;

public class AddUserToGameAction extends Action {
    private String username;

    protected AddUserToGameAction(ActionType actionType, String username, String gameId) {
        super(actionType);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.addUserToGame;
    }
}
