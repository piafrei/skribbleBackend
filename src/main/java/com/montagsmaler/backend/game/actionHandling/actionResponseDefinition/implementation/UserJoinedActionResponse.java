package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.datatransferObjects.GameIntermediateStatusDTO;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;

import java.util.List;

public class UserJoinedActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.userJoined;

    private boolean hasGameStarted;
    private List<GameUserDTO> allUser;
    private GameIntermediateStatusDTO gameIntermediateStatus;

    public UserJoinedActionResponse(List<GameUserDTO> allUser, GameIntermediateStatusDTO gameIntermediateStatusDTO, boolean hasGameStarted) {
        super(ACTION_RESPONSE_TYPE);
        this.allUser = allUser;
        this.allUser = allUser;
        this.gameIntermediateStatus = gameIntermediateStatusDTO;
        this.hasGameStarted = hasGameStarted;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
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

    public List<GameUserDTO> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<GameUserDTO> allUser) {
        this.allUser = allUser;
    }
}
