package com.montagsmaler.backend.game.canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Canvas {
    @Id
    String canvasId;
    String gameId;
    Pencilweight activePencilweight;
    String drawcolor;

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
