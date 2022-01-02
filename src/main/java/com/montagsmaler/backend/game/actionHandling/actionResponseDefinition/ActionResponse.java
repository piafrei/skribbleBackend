package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "actionResponseType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserJoinedActionResponse.class, name = "userJoined"),
        @JsonSubTypes.Type(value = StartGameActionResponse.class, name = "gameWillStart"),
        @JsonSubTypes.Type(value = ChatActionResponse.class, name = "chatMessage"),
        @JsonSubTypes.Type(value = RoundStatisticActionResponse.class, name = "roundStatistics"),
        @JsonSubTypes.Type(value = NewRoundActionResponse.class, name = "newRound"),
        @JsonSubTypes.Type(value = DrawerWordActionResponse.class, name = "drawerInformation"),
        @JsonSubTypes.Type(value = GameEndedRankingActionResponse.class, name = "gameEndedRanking"),
        @JsonSubTypes.Type(value = DrawActionResponse.class, name = "draw")
})
public abstract class ActionResponse {
    private ActionResponseType actionResponseType;

    protected ActionResponse(ActionResponseType actionResponseType) {
        this.actionResponseType = actionResponseType;
    }

    public abstract ActionResponseType getActionResponseType();

}
