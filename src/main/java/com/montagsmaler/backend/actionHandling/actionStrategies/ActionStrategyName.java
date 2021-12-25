package com.montagsmaler.backend.actionHandling.actionStrategies;

import com.montagsmaler.backend.actionHandling.actionInput.ActionType;

public enum ActionStrategyName {
    ChatActionStrategy(ActionType.chat),DrawActionStrategy(ActionType.draw),AddUserToGameStrategy(ActionType.addUserToGame),StartGameActionStrategy(ActionType.startGame);

    ActionType actionType;

    ActionStrategyName(ActionType actionType) {
        this.actionType = actionType;
    }
}
