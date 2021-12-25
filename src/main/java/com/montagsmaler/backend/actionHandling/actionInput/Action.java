package com.montagsmaler.backend.actionHandling.actionInput;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "actionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatAction.class, name = "chat"),
        @JsonSubTypes.Type(value = DrawAction.class, name = "draw"),
        @JsonSubTypes.Type(value = AddUserToGameAction.class, name = "addUserToGame")
})
public abstract class Action {
    private ActionType actionType;
    private String gameId;

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
}
