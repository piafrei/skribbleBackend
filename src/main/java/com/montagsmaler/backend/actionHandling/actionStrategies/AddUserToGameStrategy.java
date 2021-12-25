package com.montagsmaler.backend.actionHandling.actionStrategies;

import com.montagsmaler.backend.actionHandling.actionInput.Action;
import com.montagsmaler.backend.actionHandling.actionInput.AddUserToGameAction;
import com.montagsmaler.backend.actionHandling.actionResponse.ActionResponse;
import com.montagsmaler.backend.actionHandling.actionResponse.UserJoinedActionResponse;
import com.montagsmaler.backend.game.GameService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class AddUserToGameStrategy implements ActionStrategy {
    @Resource
    GameService gameService;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside addUserToGame action");

        if (!(action instanceof AddUserToGameAction)){
            System.out.println("Wrong action type");
            return Optional.empty();
        }
        AddUserToGameAction addUserToGameAction = (AddUserToGameAction) action;
        gameService.addUserToGame(addUserToGameAction.getGameId(),addUserToGameAction.getUsername());
        return Optional.of(new UserJoinedActionResponse(addUserToGameAction.getUsername()));
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.AddUserToGameStrategy;
    }
}
