package com.montagsmaler.backend.game.wordsToGuess;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordService {
    @Resource
    WordRepository wordRepository;

    public void updateWords(List<Word> wordList) {
        wordRepository.saveAll(wordList);
    }

    public Word getRandomWord(){
        List<Word> words = wordRepository.findAll();
        if(!CollectionUtils.isEmpty(words)){
            int randomNum = ThreadLocalRandom.current().nextInt(0, words.size()) -1;
            return words.get(randomNum);
        }
        return null;
    }
}
