package com.montagsmaler.backend.game.canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PixelEntity {
    private String drawcolor;
    private Pencilweight pencilweight;
    @Id
    private PixelIdentifier pixelIdentifier;

    public PixelEntity(PixelDTO pixelDTO, String canvasId){
        this.drawcolor = pixelDTO.getDrawcolor();
        this.pencilweight = pixelDTO.getPencilweight();
        this.pixelIdentifier = new PixelIdentifier(canvasId, pixelDTO.getxStartPosition(), pixelDTO.getyStartPosition(), pixelDTO.getyEndPosition(), pixelDTO.getxEndPosition());
    }

    public Pencilweight getPencilweight() {
        return pencilweight;
    }

    public void setPencilweight(Pencilweight pencilweight) {
        this.pencilweight = pencilweight;
    }

    public String getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(String drawcolor) {
        this.drawcolor = drawcolor;
    }

    public PixelIdentifier getPixelIdentifier() {
        return pixelIdentifier;
    }

    public void setPixelIdentifier(PixelIdentifier pixelIdentifier) {
        this.pixelIdentifier = pixelIdentifier;
    }
}
