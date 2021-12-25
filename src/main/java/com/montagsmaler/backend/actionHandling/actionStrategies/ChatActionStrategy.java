package com.montagsmaler.backend.actionHandling.actionStrategies;

import com.montagsmaler.backend.actionHandling.actionInput.Action;
import com.montagsmaler.backend.actionHandling.actionResponse.ActionResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatActionStrategy implements ActionStrategy {
    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside chat action");
        return Optional.empty();
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.ChatActionStrategy;
    }
}
