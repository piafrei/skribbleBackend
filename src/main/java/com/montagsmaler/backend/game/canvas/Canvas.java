package com.montagsmaler.backend.game.canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Canvas {
    @Id
    String canvasId;
    String gameId;
    Characterweight activeCharacterweight;
    Drawcolor drawcolor;

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
    }

    public Characterweight getActiveCharacterweight() {
        return activeCharacterweight;
    }

    public void setActiveCharacterweight(Characterweight activeCharacterweight) {
        this.activeCharacterweight = activeCharacterweight;
    }

    public Drawcolor getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(Drawcolor drawcolor) {
        this.drawcolor = drawcolor;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
