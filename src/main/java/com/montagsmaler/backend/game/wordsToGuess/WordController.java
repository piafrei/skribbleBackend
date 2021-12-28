package com.montagsmaler.backend.game.wordsToGuess;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {
    @Resource
    WordService wordService;

    @PostMapping
    public ResponseEntity updateWords(@RequestBody List<Word> wordList) {
        try {
            wordService.updateWords(wordList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
