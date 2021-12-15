package com.montagsmaler.backend.controller;

import com.montagsmaler.backend.controller.ActionInput.ActionType;

public interface ActionStrategy {
    void doStuff();
    ActionStrategyName getStrategyName();
}
