package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.canvas.CanvasService;
import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.ClearCanvasActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.GameService;

import javax.annotation.Resource;
import java.util.Optional;

public class ClearCanvasStrategy implements ActionStrategy{
    @Resource
    CanvasService canvasService;

    @Resource
    GameService gameService;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        String canvasIdByGameId = canvasService.getCanvasIdByGameId(action.getGameId());
        if(canvasIdByGameId != null){
            canvasService.clearCanvas(canvasIdByGameId);
        }
        return Optional.of(new ClearCanvasActionResponse());
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.ClearCanvasActionStrategy;
    }
}
