package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.canvas.PixelDTO;

import java.util.List;

public class DrawActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.drawAction;

    private List<PixelDTO> pixelToUpdate;

    public DrawActionResponse(List<PixelDTO> pixelToUpdate) {
        super(ACTION_RESPONSE_TYPE);
        this.pixelToUpdate = pixelToUpdate;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }

    public List<PixelDTO> getPixelToUpdate() {
        return pixelToUpdate;
    }

    public void setPixelToUpdate(List<PixelDTO> pixelToUpdate) {
        this.pixelToUpdate = pixelToUpdate;
    }
}
