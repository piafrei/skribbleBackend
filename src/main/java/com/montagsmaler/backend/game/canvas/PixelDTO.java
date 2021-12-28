package com.montagsmaler.backend.game.canvas;

public class PixelDTO {
    Drawcolor drawcolor;
    int yPosition;
    int xPosition;

    public PixelDTO() {
    }

    public PixelDTO(PixelEntity pixelEntity) {
        this.drawcolor = pixelEntity.getDrawcolor();
        this.yPosition = pixelEntity.getPixelIdentifier().getyPosition();
        this.xPosition = pixelEntity.getPixelIdentifier().getxPosition();
    }

    public Drawcolor getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(Drawcolor drawcolor) {
        this.drawcolor = drawcolor;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }
}
