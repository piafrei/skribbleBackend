package com.montagsmaler.backend.game.wordsToGuess;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WordRepository extends MongoRepository<Word, String> {
    List<Word> getByCategoryAndDifficulty(Category category, Difficulty difficulty);
}
