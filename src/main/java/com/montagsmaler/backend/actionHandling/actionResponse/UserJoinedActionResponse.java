package com.montagsmaler.backend.actionHandling.actionResponse;

public class UserJoinedActionResponse extends ActionResponse {
    private String username;

    public UserJoinedActionResponse(String username) {
        super(ActionResponseType.userJoined);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ActionResponseType.userJoined;
    }
}
