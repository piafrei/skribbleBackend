package com.montagsmaler.backend.controller.ActionStrategies;

import com.montagsmaler.backend.controller.ActionStrategy;
import com.montagsmaler.backend.controller.ActionStrategyName;
import org.springframework.stereotype.Component;

@Component
public class ChatActionStrategy implements ActionStrategy {
    @Override
    public void doStuff() {
        System.out.print("inside chat action");
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.ChatActionStrategy;
    }
}
