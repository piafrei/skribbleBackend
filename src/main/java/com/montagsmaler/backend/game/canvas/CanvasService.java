package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.GameService;
import com.montagsmaler.backend.game.canvas.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            CanvasDTO canvasDTO = new CanvasDTO(canvas.getActiveCharacterweight(), canvas.getDrawcolor());
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

    public void updatePixel(String gameId, List<PixelDTO> pixelToUpdate) {
        Canvas canvas = canvasRepository.findByGameId(gameId);
        if(canvas != null){
            String canvasId = canvas.getCanvasId();
            List<PixelEntity> pixelEntityList = pixelToUpdate.stream()
                    .map(pixelDTO -> new PixelEntity(pixelDTO, canvasId))
                    .collect(Collectors.toList());
            pixelRepository.saveAll(pixelEntityList);
        }
    }

    public void clearCanvas(String canvasId) {
        pixelRepository.deleteByPixelIdentifier_CanvasId(canvasId);
    }
}
