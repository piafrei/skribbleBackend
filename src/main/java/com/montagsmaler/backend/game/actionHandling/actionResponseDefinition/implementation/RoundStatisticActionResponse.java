package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;

import java.util.List;
import java.util.Map;

public class RoundStatisticActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.roundStatistics;

    int roundNumber;
    String correctWord;
    Map<String, Integer> roundPoints;
    List<RankingDTO> overallRanking;

    public RoundStatisticActionResponse(int roundNumber, String correctWord, Map<String, Integer> roundPoints, List<RankingDTO> overallRanking) {
        super(ACTION_RESPONSE_TYPE);
        this.roundNumber = roundNumber;
        this.overallRanking = overallRanking;
        this.correctWord = correctWord;
        this.roundPoints = roundPoints;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public Map<String, Integer> getRoundPoints() {
        return roundPoints;
    }

    public void setRoundPoints(Map<String, Integer> roundPoints) {
        this.roundPoints = roundPoints;
    }

    public List<RankingDTO> getOverallRanking() {
        return overallRanking;
    }

    public void setOverallRanking(List<RankingDTO> overallRanking) {
        this.overallRanking = overallRanking;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }


    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }
}
