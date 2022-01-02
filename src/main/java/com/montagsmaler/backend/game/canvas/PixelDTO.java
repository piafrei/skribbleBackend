package com.montagsmaler.backend.game.canvas;

public class PixelDTO {
    Pencilweight pencilweight;
    Drawcolor drawcolor;
    int yStartPosition;
    int xStartPosition;
    int yEndPosition;
    int xEndPosition;

    public PixelDTO() {
    }

    public PixelDTO(PixelEntity pixelEntity) {
        this.drawcolor = pixelEntity.getDrawcolor();
        this.pencilweight = pixelEntity.getPencilweight();
        this.yStartPosition = pixelEntity.getPixelIdentifier().getyStartPosition();
        this.xStartPosition = pixelEntity.getPixelIdentifier().getxStartPosition();
        this.yEndPosition = pixelEntity.getPixelIdentifier().getyEndPosition();
        this.xEndPosition = pixelEntity.getPixelIdentifier().getxEndPosition();
    }

    public Pencilweight getPencilweight() {
        return pencilweight;
    }

    public void setPencilweight(Pencilweight pencilweight) {
        this.pencilweight = pencilweight;
    }

    public int getyEndPosition() {
        return yEndPosition;
    }

    public void setyEndPosition(int yEndPosition) {
        this.yEndPosition = yEndPosition;
    }

    public int getxEndPosition() {
        return xEndPosition;
    }

    public void setxEndPosition(int xEndPosition) {
        this.xEndPosition = xEndPosition;
    }


    public Drawcolor getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(Drawcolor drawcolor) {
        this.drawcolor = drawcolor;
    }

    public int getyStartPosition() {
        return yStartPosition;
    }

    public void setyStartPosition(int yStartPosition) {
        this.yStartPosition = yStartPosition;
    }

    public int getxStartPosition() {
        return xStartPosition;
    }

    public void setxStartPosition(int xStartPosition) {
        this.xStartPosition = xStartPosition;
    }
}
