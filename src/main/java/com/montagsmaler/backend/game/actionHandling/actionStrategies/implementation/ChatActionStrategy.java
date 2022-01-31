package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.gameEvents.implementation.AllUserGuessedWordEvent;
import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.ChatAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.ChatActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.GameService;
import com.montagsmaler.backend.game.gameEvents.EventHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class ChatActionStrategy implements ActionStrategy {
    @Resource
    private GameService gameService;

    @Resource
    private EventHandler eventHandler;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        if (!(action instanceof ChatAction)) {
            System.out.println("Wrong action type");
            return Optional.empty();
        }

        ChatAction chatAction = (ChatAction) action;
        boolean isWordCorrect = checkIsWordCorrect(chatAction);

        if (isWordCorrect) {
            boolean allUserGuessedWord = gameService.checkAllUserGuessedWord(chatAction.getGameId());
            if(allUserGuessedWord){
                publishAllUserGuessedWordEvent(chatAction.getGameId());
            }
        }
        return Optional.of(new ChatActionResponse(chatAction.getMessage(), chatAction.getUsername(), isWordCorrect));
    }

    private boolean checkIsWordCorrect(ChatAction chatAction) {
        return gameService.checkIsWordCorrect(chatAction.getGameId(), chatAction.getMessage(), chatAction.getUsername());
    }

    private void publishAllUserGuessedWordEvent(final String gameId) {
        AllUserGuessedWordEvent allUserGuessedWordEvent = new AllUserGuessedWordEvent(gameId);
        eventHandler.notifyUpdate(allUserGuessedWordEvent);
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.ChatActionStrategy;
    }
}
