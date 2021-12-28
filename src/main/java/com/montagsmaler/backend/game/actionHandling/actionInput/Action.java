package com.montagsmaler.backend.game.actionHandling.actionInput;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.*;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "actionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatAction.class, name = "chat"),
        @JsonSubTypes.Type(value = DrawAction.class, name = "draw"),
        @JsonSubTypes.Type(value = AddUserToGameAction.class, name = "addUserToGame"),
        @JsonSubTypes.Type(value = StartGameAction.class, name = "startGame"),
        @JsonSubTypes.Type(value = ClearCanvasAction.class, name = "clearCanvas")
})
public abstract class Action {
    private ActionType actionType;
    private String gameId;
    private String username;

    protected Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public abstract ActionType getActionType();

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
