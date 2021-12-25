package com.montagsmaler.backend.actionHandling.actionStrategies;

import com.montagsmaler.backend.actionHandling.actionInput.Action;
import com.montagsmaler.backend.actionHandling.actionResponse.ActionResponse;

import java.util.Optional;

public interface ActionStrategy {
    Optional<ActionResponse> executeAction(Action action);
    ActionStrategyName getStrategyName();
}
