package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.datatransferObjects.GameIntermediateStatusDTO;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;

public class UserJoinedActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.userJoined;

    private boolean hasGameStarted;
    private GameUserDTO user;
    private GameIntermediateStatusDTO gameIntermediateStatus;

    public UserJoinedActionResponse(GameUserDTO gameUserDTO, GameIntermediateStatusDTO gameIntermediateStatusDTO, boolean hasGameStarted) {
        super(ACTION_RESPONSE_TYPE);
        this.user = gameUserDTO;
        this.gameIntermediateStatus = gameIntermediateStatusDTO;
        this.hasGameStarted = hasGameStarted;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }

    public GameUserDTO getUser() {
        return user;
    }

    public void setUser(GameUserDTO user) {
        this.user = user;
    }

    public GameIntermediateStatusDTO getGameIntermediateStatus() {
        return gameIntermediateStatus;
    }

    public void setGameIntermediateStatus(GameIntermediateStatusDTO gameIntermediateStatus) {
        this.gameIntermediateStatus = gameIntermediateStatus;
    }

    public boolean isHasGameStarted() {
        return hasGameStarted;
    }

    public void setHasGameStarted(boolean hasGameStarted) {
        this.hasGameStarted = hasGameStarted;
    }
}
