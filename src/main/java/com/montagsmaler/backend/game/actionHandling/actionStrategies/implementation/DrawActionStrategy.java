package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.canvas.CanvasService;
import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.DrawAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.DrawActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.canvas.Drawcolor;
import com.montagsmaler.backend.game.canvas.PixelDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DrawActionStrategy implements ActionStrategy {
    @Resource
    private CanvasService canvasService;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside draw action");

        if (!(action instanceof DrawAction)){
            System.out.println("Wrong action type");
            return Optional.empty();
        }

        DrawAction drawAction = (DrawAction) action;
        PixelDTO pixelToUpdate = drawAction.getPixelToUpdate();
        validateDrawColor(pixelToUpdate);
        canvasService.updatePixel(action.getGameId(), pixelToUpdate);
        return Optional.of(new DrawActionResponse(pixelToUpdate));
    }

    private void validateDrawColor(PixelDTO pixelToUpdate) {
        String hexCodeForColor = pixelToUpdate.getDrawcolor();
        Drawcolor drawcolor = Drawcolor.fromString(hexCodeForColor);
        boolean inputHexCodeIsInvalidColor = !drawcolor.getHexcode().equals(hexCodeForColor);
        if (inputHexCodeIsInvalidColor){
            setPixelColorToReturnedDefaultColor(pixelToUpdate, drawcolor);
        }
    }

    private void setPixelColorToReturnedDefaultColor(PixelDTO pixelToUpdate, Drawcolor drawcolor) {
        pixelToUpdate.setDrawcolor(drawcolor.getHexcode());
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.DrawActionStrategy;
    }
}
