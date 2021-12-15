package com.montagsmaler.backend.controller;

import com.montagsmaler.backend.controller.ActionInput.ActionType;

import java.util.Arrays;

public enum ActionStrategyName {
    ChatActionStrategy(ActionType.chat),DrawActionStrategy(ActionType.draw);

    ActionType actionType;

    ActionStrategyName(ActionType actionType) {
        this.actionType = actionType;
    }
}
