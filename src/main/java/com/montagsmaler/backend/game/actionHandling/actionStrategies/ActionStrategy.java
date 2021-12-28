package com.montagsmaler.backend.game.actionHandling.actionStrategies;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;

import java.util.Optional;

public interface ActionStrategy {
    Optional<ActionResponse> executeAction(Action action);
    ActionStrategyName getStrategyName();
}
