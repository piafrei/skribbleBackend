package com.montagsmaler.backend.game.canvas;

public class PixelIdentifier {
    String canvasId;
    Integer yStartPosition;
    Integer xStartPosition;
    Integer yEndPosition;
    Integer xEndPosition;

    public PixelIdentifier(String canvasId, Integer xStartPosition, Integer yStartPosition, Integer yEndPosition, Integer xEndPosition) {
        this.canvasId = canvasId;
        this.yStartPosition = yStartPosition;
        this.xStartPosition = xStartPosition;
        this.yEndPosition = yEndPosition;
        this.xEndPosition = xEndPosition;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
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


}
