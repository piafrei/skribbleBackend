package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.AllUserGuessedWordEvent;
import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.ChatAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.ChatActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.GameService;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class ChatActionStrategy implements ActionStrategy {
    @Resource
    GameService gameService;

    @Resource
    UserDetailServiceImpl userDetailService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside chat action");

        if (!(action instanceof ChatAction)){
            System.out.println("Wrong action type");
            return Optional.empty();
        }

        ChatAction chatAction = (ChatAction) action;
        boolean isWordCorrect = gameService.checkIsWordCorrect(chatAction.getGameId(), chatAction.getMessage(), chatAction.getUsername());

        publishCustomEvent(chatAction.getGameId());
        ChatActionResponse actionResponse = new ChatActionResponse(chatAction.getMessage(), chatAction.getUsername(), isWordCorrect);
        return Optional.of(actionResponse);
    }

    public void publishCustomEvent(final String gameId) {
        System.out.println("Publishing custom event. ");
        AllUserGuessedWordEvent customSpringEvent = new AllUserGuessedWordEvent(this, gameId);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.ChatActionStrategy;
    }
}
