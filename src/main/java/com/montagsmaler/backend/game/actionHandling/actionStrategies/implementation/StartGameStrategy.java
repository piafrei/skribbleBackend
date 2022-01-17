package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.StartGameAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.StartGameActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.RepeatedRoundTasksExecutor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartGameStrategy implements ActionStrategy {

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside start game action");

        if (!(action instanceof StartGameAction)){
            System.out.println("Wrong action type");
            return Optional.empty();
        }

        StartGameAction startGameAction = (StartGameAction) action;

        Thread thread = new Thread(new RepeatedRoundTasksExecutor(action.getGameId()));
        thread.start();

        return Optional.of(new StartGameActionResponse());
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.StartGameActionStrategy;
    }
}

