package com.montagsmaler.backend.game;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<GameEntity, String> {
}
