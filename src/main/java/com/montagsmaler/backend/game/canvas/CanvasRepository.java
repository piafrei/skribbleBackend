package com.montagsmaler.backend.game.canvas;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CanvasRepository extends MongoRepository<Canvas, String> {
    Canvas findByGameId(String gameId);
}
