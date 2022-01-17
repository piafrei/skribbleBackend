package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class GameController {
    @Resource
    private ActionStrategyFactory strategyFactory;
    @Resource
    private GameService gameService;
    @Resource
    private GameStatisticService gameStatisticService;

    @RequestMapping(value="/checkAuth")
    public ResponseEntity checkAuthState() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/gameStatistic")
    public List<GameStatisticEntity> getGameStatistic() {
        String user = getUserNameFromContext();
        return gameStatisticService.getGameStatisticsForUser(user);
    }

    @PostMapping(value="/backend/game")
    public String createGame() {
        String user = getUserNameFromContext();
        return gameService.createNewGame(user);
    }

    @MessageMapping("/game/{gameId}")
    @SendTo("/updates/{gameId}")
    public Optional<ActionResponse> gameSpecificGreeting(@DestinationVariable String gameId, Action action) {
        ActionStrategy strategy = strategyFactory.findActionStrategyByActionName(action.getActionType());
        action.setGameId(gameId);
        action.setUsername(getUserNameFromContext());

        return strategy.executeAction(action);
    }

    private String getUserNameFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
