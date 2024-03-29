package com.montagsmaler.backend.game.canvas;

import java.util.List;

public class CanvasDTO {
    private Pencilweight activePencilweight;
    private String drawcolor;
    private List<PixelDTO> elementList;

    CanvasDTO(Pencilweight activePencilweight, String drawcolor) {
        this.activePencilweight = activePencilweight;
        this.drawcolor = drawcolor;
    }

    public Pencilweight getActivePencilweight() {
        return activePencilweight;
    }

    public void setActivePencilweight(Pencilweight activePencilweight) {
        this.activePencilweight = activePencilweight;
    }

    public String getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(String drawcolor) {
        this.drawcolor = drawcolor;
    }

    public List<PixelDTO> getElementList() {
        return elementList;
    }

    public void setElementList(List<PixelDTO> elementList) {
        this.elementList = elementList;
    }
}
