package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CanvasService {
    @Resource
    PixelRepository pixelRepository;

    @Resource
    CanvasRepository canvasRepository;

    @Resource
    GameService gameService;

    public Optional<CanvasDTO> getCanvasDTO(GameEntity gameEntity){
        Canvas canvas = canvasRepository.findByGameId(gameEntity.getGameId());
        if (canvas != null){
            CanvasDTO canvasDTO = new CanvasDTO(canvas.getActivePencilweight(), canvas.getDrawcolor());
            List<PixelDTO> canvasPixelEntity = pixelRepository.getByPixelIdentifier_CanvasId(canvas.getCanvasId());
            if(!CollectionUtils.isEmpty(canvasPixelEntity)){
                canvasDTO.setElementList(canvasPixelEntity);
            }
            return Optional.of(canvasDTO);
        }
        return Optional.empty();
    }

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
