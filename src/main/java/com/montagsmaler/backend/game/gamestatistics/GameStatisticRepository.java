package com.montagsmaler.backend.game.gamestatistics;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface GameStatisticRepository extends MongoRepository<GameStatisticEntity, String> {
    int countByUserID(UUID userID);
    List<GameStatisticEntity> getByUserIDOrderByPlayerGameNumberAsc(UUID userID);
}
