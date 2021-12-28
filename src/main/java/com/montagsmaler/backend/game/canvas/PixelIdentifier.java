package com.montagsmaler.backend.game.canvas;

public class PixelIdentifier {
    String canvasId;
    int yPosition;
    int xPosition;

    public PixelIdentifier(String canvasId, int xPosition, int yPosition) {
        this.canvasId = canvasId;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
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
