package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.canvas.Drawcolor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PixelEntity {
    Drawcolor drawcolor;
    @Id
    PixelIdentifier pixelIdentifier;

    public PixelEntity(PixelDTO pixelDTO, String canvasId){
        this.drawcolor = pixelDTO.getDrawcolor();
        this.pixelIdentifier = new PixelIdentifier(canvasId, pixelDTO.getxPosition(), pixelDTO.getyPosition());
    }

    public Drawcolor getDrawcolor() {
        return drawcolor;
    }

    public void setDrawcolor(Drawcolor drawcolor) {
        this.drawcolor = drawcolor;
    }

    public PixelIdentifier getPixelIdentifier() {
        return pixelIdentifier;
    }

    public void setPixelIdentifier(PixelIdentifier pixelIdentifier) {
        this.pixelIdentifier = pixelIdentifier;
    }
}
