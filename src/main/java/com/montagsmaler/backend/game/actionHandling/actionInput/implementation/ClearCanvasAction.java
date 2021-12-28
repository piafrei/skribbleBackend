package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;

public class ClearCanvasAction extends Action {
    protected ClearCanvasAction() {
        super(ActionType.clearCanvas);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.clearCanvas;
    }
}
