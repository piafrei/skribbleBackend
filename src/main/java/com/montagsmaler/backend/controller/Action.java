package com.montagsmaler.backend.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.montagsmaler.backend.controller.ActionInput.ActionType;
import com.montagsmaler.backend.controller.ActionInput.ChatAction;
import com.montagsmaler.backend.controller.ActionInput.DrawAction;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "actionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatAction.class, name = "chat"),
        @JsonSubTypes.Type(value = DrawAction.class, name = "draw")
})
public abstract class Action {
    private ActionType actionType;

    protected Action(ActionType actionType) {
        actionType = actionType;
    }

    public abstract ActionType getActionType();
}
