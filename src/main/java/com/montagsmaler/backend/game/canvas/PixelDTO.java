package com.montagsmaler.backend.game.canvas;

public class PixelDTO {
    private Pencilweight pencilweight;
    private String drawcolor;
    private Integer yStartPosition;
    private Integer xStartPosition;
    private Integer yEndPosition;
    private Integer xEndPosition;

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

    public Integer getyEndPosition() {
        return yEndPosition;
    }

    public void setyEndPosition(Integer yEndPosition) {
        this.yEndPosition = yEndPosition;
    }

    public Integer getxEndPosition() {
        return xEndPosition;
    }

    public void setxEndPosition(Integer xEndPosition) {
        this.xEndPosition = xEndPosition;
    }


    public String getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(String drawcolor) {
        this.drawcolor = drawcolor;
    }

    public Integer getyStartPosition() {
        return yStartPosition;
    }

    public void setyStartPosition(Integer yStartPosition) {
        this.yStartPosition = yStartPosition;
    }

    public Integer getxStartPosition() {
        return xStartPosition;
    }

    public void setxStartPosition(Integer xStartPosition) {
        this.xStartPosition = xStartPosition;
    }
}
