package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;


import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;

public class UserDisconnectedActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.userDisconnected;

    private String username;
    private String host;

    public UserDisconnectedActionResponse(String username, String host) {
        super(ACTION_RESPONSE_TYPE);
        this.username = username;
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }
}
