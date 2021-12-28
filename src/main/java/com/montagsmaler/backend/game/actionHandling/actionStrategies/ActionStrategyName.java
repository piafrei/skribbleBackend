package com.montagsmaler.backend.game.actionHandling.actionStrategies;

import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;

public enum ActionStrategyName {
    ChatActionStrategy(ActionType.chat),DrawActionStrategy(ActionType.draw),AddUserToGameStrategy(ActionType.addUserToGame),StartGameActionStrategy(ActionType.startGame),
    ClearCanvasActionStrategy(ActionType.clearCanvas);

    ActionType actionType;

    ActionStrategyName(ActionType actionType) {
        this.actionType = actionType;
    }
}
