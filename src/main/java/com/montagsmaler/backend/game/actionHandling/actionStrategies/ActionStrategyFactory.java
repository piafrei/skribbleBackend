package com.montagsmaler.backend.game.actionHandling.actionStrategies;

import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ActionStrategyFactory {
    private Map<ActionStrategyName, ActionStrategy> actionStrategies;

    @Autowired
    public ActionStrategyFactory(Set<ActionStrategy> strategySet) {
        createActionStrategy(strategySet);
    }

    public ActionStrategy findActionStrategyByActionName(ActionType actionName) {
        ActionStrategyName actionType = getByActionType(actionName);
        return actionStrategies.get(actionType);
    }

    public ActionStrategy findActionStrategy(ActionStrategyName actionStrategyName) {
        return actionStrategies.get(actionStrategyName);
    }

    private ActionStrategyName getByActionType(ActionType actionType){
        return Arrays.stream(ActionStrategyName.values()).filter(value -> value.actionType.equals(actionType)).findFirst().orElse(null);
    }

    private void createActionStrategy(Set<ActionStrategy> strategySet) {
        actionStrategies = new HashMap<>();
        strategySet.forEach(actionStrategy -> actionStrategies.put(actionStrategy.getStrategyName(), actionStrategy));
    }

}
