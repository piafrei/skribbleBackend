package com.montagsmaler.backend.game.canvas;

public class PixelIdentifier {
    String canvasId;
    int yStartPosition;
    int xStartPosition;
    int yEndPosition;
    int xEndPosition;

    public PixelIdentifier(String canvasId, int xStartPosition, int yStartPosition, int yEndPosition, int xEndPosition) {
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


}
