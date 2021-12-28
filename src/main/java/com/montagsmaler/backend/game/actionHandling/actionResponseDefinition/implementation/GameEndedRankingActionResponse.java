package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;

import java.util.List;

public class GameEndedRankingActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.gameEndedRanking;

    private List<RankingDTO> resultRanking;

    public GameEndedRankingActionResponse(List<RankingDTO> rankingDTOS) {
        super(ACTION_RESPONSE_TYPE);
        this.resultRanking = rankingDTOS;
    }

    public List<RankingDTO> getResultRanking() {
        return resultRanking;
    }

    public void setResultRanking(List<RankingDTO> resultRanking) {
        this.resultRanking = resultRanking;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }
}
