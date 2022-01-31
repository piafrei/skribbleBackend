package com.montagsmaler.backend.game.wordsToGuess;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {
}
