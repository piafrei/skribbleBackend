package com.montagsmaler.backend.game.canvas;

import java.util.List;

public class CanvasDTO {
    Pencilweight activePencilweight;
    Drawcolor drawcolor;
    List<PixelDTO> elementList;

    public CanvasDTO(Pencilweight activePencilweight, Drawcolor drawcolor) {
        this.activePencilweight = activePencilweight;
        this.drawcolor = drawcolor;
    }

    public Pencilweight getActivePencilweight() {
        return activePencilweight;
    }

    public void setActivePencilweight(Pencilweight activePencilweight) {
        this.activePencilweight = activePencilweight;
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
