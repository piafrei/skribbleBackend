package com.montagsmaler.backend.game.canvas;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PixelRepository extends MongoRepository<PixelEntity, String> {
    List<PixelDTO> getByPixelIdentifier_CanvasId(String canvasId);
    void deleteByPixelIdentifier_CanvasId(String canvasId);
}
