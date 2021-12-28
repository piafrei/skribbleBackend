package com.montagsmaler.backend.game.canvas;

import com.montagsmaler.backend.game.GameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CanvasRepository extends MongoRepository<Canvas, String> {
    Canvas findByGameId(String gameId);
}
