package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;
import com.montagsmaler.backend.game.canvas.PixelDTO;

public class DrawAction extends Action {
    private PixelDTO pixelToUpdate;

    public DrawAction(ActionType actionType, PixelDTO pixelToUpdate) {
        super(actionType);
        this.pixelToUpdate = pixelToUpdate;
    }

    public PixelDTO getPixelToUpdate() {
        return pixelToUpdate;
    }

    public void setPixelToUpdate(PixelDTO pixelToUpdate) {
        this.pixelToUpdate = pixelToUpdate;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.draw;
    }
}
