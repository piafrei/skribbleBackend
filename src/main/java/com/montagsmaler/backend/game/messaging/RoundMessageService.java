package com.montagsmaler.backend.game.messaging;

import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.Gameround;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.DrawerWordActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.GameEndedRankingActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.NewRoundActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.RoundStatisticActionResponse;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoundMessageService {
    @Resource
    private StompTemplateMessageService messageService;

    public void sendRoundStatistics(GameEntity game, Gameround gameround, GameEntity updatedGame, Map<String, Integer> roundPoints,List<RankingDTO> rankingDTOS) {
        messageService.sendScheduledUpdate(game.getGameId(), new RoundStatisticActionResponse(updatedGame.getActiveRound().getRoundNumber(), gameround.getActiveWord().getValue(), roundPoints, rankingDTOS));
    }

    public void sendNewRoundInformationToAllUser(String gameId, GameUserDTO drawer, Gameround gameround) {
        messageService.sendScheduledUpdate(gameId, new NewRoundActionResponse(drawer, gameround.getActiveWord().getWordLenth(), gameround.getRoundNumber()));
    }

    public void sendWordToDrawToCurrentDrawer(String gameId, GameUserDTO drawer, Gameround gameround) {
        messageService.sendToSpecificUser(gameId, drawer.getUsername(), new DrawerWordActionResponse(gameround.getActiveWord()));
    }

    public void sendGameEndedMessage(String gameId, List<RankingDTO> rankingDTOS) {
        messageService.sendScheduledUpdate(gameId, new GameEndedRankingActionResponse(rankingDTOS));
    }
}
