package com.montagsmaler.backend.actionHandling.actionResponse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.montagsmaler.backend.actionHandling.actionInput.ActionType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "actionResponseType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserJoinedActionResponse.class, name = "userJoined"),
})
public abstract class ActionResponse {
    private ActionResponseType actionResponseType;

    protected ActionResponse(ActionResponseType actionResponseType) {
        this.actionResponseType = actionResponseType;
    }

    public abstract ActionResponseType getActionResponseType();

}
