package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.canvas.Characterweight;
import com.montagsmaler.backend.game.canvas.Drawcolor;
import com.montagsmaler.backend.game.canvas.PixelDTO;

import java.util.List;

public class CanvasDTO {
    Characterweight activeCharacterweight;
    Drawcolor drawcolor;
    List<PixelDTO> elementList;

    public CanvasDTO(Characterweight activeCharacterweight, Drawcolor drawcolor) {
        this.activeCharacterweight = activeCharacterweight;
        this.drawcolor = drawcolor;
    }

    public Characterweight getActiveCharacterweight() {
        return activeCharacterweight;
    }

    public void setActiveCharacterweight(Characterweight activeCharacterweight) {
        this.activeCharacterweight = activeCharacterweight;
    }

    public Drawcolor getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(Drawcolor drawcolor) {
        this.drawcolor = drawcolor;
    }

    public List<PixelDTO> getElementList() {
        return elementList;
    }

    public void setElementList(List<PixelDTO> elementList) {
        this.elementList = elementList;
    }
}
