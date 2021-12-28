package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.canvas.Canvas;
import com.montagsmaler.backend.game.canvas.CanvasRepository;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.wordsToGuess.WordService;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    @Resource
    UserDetailServiceImpl userDetailsService;

    @Resource
    GameRepository gameRepository;

    @Resource
    WordService wordService;

    @Resource
    UserDetailServiceImpl userDetailService;

    @Resource
    CanvasRepository canvasRepository;

    public Optional<GameEntity> getGameById(String gameId){
        return gameRepository.findById(gameId);
    }

    public String createNewGame(String createrUserName) {
        GameEntity game = new GameEntity();
        game.addPlayer(createrUserName);
        game.setHost(createrUserName);
        gameRepository.save(game);
        Canvas canvas = new Canvas();
        canvas.setGameId(game.getGameId());
        canvasRepository.save(canvas);
        return game.getGameId();
    }

    public Optional<GameEntity> addUserToGame(String gameId, String username) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if(gameEntity.isPresent()){
            GameEntity game = gameEntity.get();
            game.addPlayer(username);
            gameRepository.save(game);
            return gameEntity;
        }
        return Optional.empty();
    }

    public boolean checkIsWordCorrect(String gameId, String message, String username) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        System.out.println(message + " " + gameEntity.get().getActiveRound().getActiveWord().getValue());
        if(gameEntity.isPresent() && gameEntity.get().getActiveRound().getActiveWord().getValue().equals(message)){
            GameEntity game = gameEntity.get();
            game.getActiveRound().addRightGuessedUser(username);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    public Gameround startRound(GameEntity game, int roundNumber, String player) {
        Gameround activeRound = new Gameround();
        activeRound.setRoundNumber(roundNumber);
        activeRound.setActiveWord(wordService.getRandomWord());
        activeRound.setDrawer(player);
        game.setActiveRound(activeRound);
        gameRepository.save(game);
        return activeRound;
    }

    public Map<GameUserDTO, Integer> parsePlayerToScoreMap(Map<String, Integer> playerToScoreMap) {
        Map<GameUserDTO, Integer> parsedPlayerToScoreMap = new HashMap<>();
        for (var entry : playerToScoreMap.entrySet()) {
            parsedPlayerToScoreMap.put(userDetailService.getGameUserByName(entry.getKey()).get(),entry.getValue());
        }
        return parsedPlayerToScoreMap;
    }

    void updateOverallPoints(String gameId, Map<String, Integer> roundPoints) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if(gameEntity.isPresent()){
            GameEntity entity = gameEntity.get();
            Map<String, Integer> playerToOverallScoreMap = entity.getPlayerToOverallScoreMap();
            for (var entry : roundPoints.entrySet()) {
                Integer lastOverallScoreValueForPlayer = playerToOverallScoreMap.get(entry.getKey());
                playerToOverallScoreMap.put(entry.getKey(),entry.getValue() + lastOverallScoreValueForPlayer);
            }
            entity.setPlayerToOverallScoreMap(playerToOverallScoreMap);
            gameRepository.save(entity);
        }
    }
}
