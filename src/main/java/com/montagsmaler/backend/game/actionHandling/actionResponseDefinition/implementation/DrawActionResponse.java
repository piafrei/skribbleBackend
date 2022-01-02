package com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponseType;
import com.montagsmaler.backend.game.canvas.PixelDTO;

import java.util.List;

public class DrawActionResponse extends ActionResponse {
    private static final ActionResponseType ACTION_RESPONSE_TYPE = ActionResponseType.draw;

    private PixelDTO pixelToUpdate;

    public DrawActionResponse(PixelDTO pixelToUpdate) {
        super(ACTION_RESPONSE_TYPE);
        this.pixelToUpdate = pixelToUpdate;
    }

    @Override
    public ActionResponseType getActionResponseType() {
        return ACTION_RESPONSE_TYPE;
    }

    public PixelDTO getPixelToUpdate() {
        return pixelToUpdate;
    }

    public void setPixelToUpdate(PixelDTO pixelToUpdate) {
        this.pixelToUpdate = pixelToUpdate;
    }
}
