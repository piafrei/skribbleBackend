package com.montagsmaler.backend.game.wordsToGuess;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordService {
    @Resource
    private WordRepository wordRepository;

    void updateWords(List<Word> wordList) {
        wordRepository.saveAll(wordList);
    }

    public Word getRandomWord(){
        List<Word> words = wordRepository.findAll();
        if(!CollectionUtils.isEmpty(words)){
            int randomNum = ThreadLocalRandom.current().nextInt(0, words.size());
            return words.get(randomNum);
        }
        return null;
    }
}
