package com.montagsmaler.backend.game;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends MongoRepository<GameEntity, String> {
    @Query("{'playerToOverallScoreMap.?0':{'$exists': 1}}")
    List<GameEntity> findByPlayerToOverallScoreMapIsContaining(String username);
}
