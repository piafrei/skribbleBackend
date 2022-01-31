package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service
public class CanvasService {
    @Resource
    private PixelRepository pixelRepository;

    @Resource
    private CanvasRepository canvasRepository;


    public String getCanvasIdByGameId(String gameId){
        Canvas canvas = canvasRepository.findByGameId(gameId);
        if (canvas != null){
           return canvas.getCanvasId();
        }
        return null;
    }

    public void updatePixel(String gameId, PixelDTO pixelToUpdate) {
        Canvas canvas = canvasRepository.findByGameId(gameId);
        if(canvas != null){
            String canvasId = canvas.getCanvasId();
            PixelEntity pixelEntity = new PixelEntity(pixelToUpdate, canvasId);
            pixelRepository.save(pixelEntity);
        }
    }

    public void clearCanvas(String canvasId) {
        pixelRepository.deleteByPixelIdentifier_CanvasId(canvasId);
    }
}
