package com.montagsmaler.backend.game.actionHandling.actionInput.implementation;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.ActionType;
import com.montagsmaler.backend.game.canvas.PixelDTO;
import java.util.List;

public class DrawAction extends Action {
    private List<PixelDTO> pixelToUpdate;

    public DrawAction(ActionType actionType, List<PixelDTO> pixelToUpdate) {
        super(actionType);
        this.pixelToUpdate = pixelToUpdate;
    }

    public List<PixelDTO> getPixelToUpdate() {
        return pixelToUpdate;
    }

    public void setPixelToUpdate(List<PixelDTO> pixelToUpdate) {
        this.pixelToUpdate = pixelToUpdate;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.draw;
    }
}
