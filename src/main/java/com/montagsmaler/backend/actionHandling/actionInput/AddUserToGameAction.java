package com.montagsmaler.backend.actionHandling.actionInput;

public class AddUserToGameAction extends Action {
    private String username;
    private String gameId;

    protected AddUserToGameAction(ActionType actionType, String username, String gameId) {
        super(actionType);
        this.username = username;
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.addUserToGame;
    }
}
