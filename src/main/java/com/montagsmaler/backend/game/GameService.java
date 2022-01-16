package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.canvas.Canvas;
import com.montagsmaler.backend.game.canvas.CanvasRepository;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.wordsToGuess.WordService;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    GameStatisticRepository gameStatisticRepository;

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
        if(gameEntity.isPresent() && gameEntity.get().getActiveRound().getActiveWord().getValue().equals(message)){
            System.out.println(message + " " + gameEntity.get().getActiveRound().getActiveWord().getValue());

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

    Map<String, Integer> updateOverallPoints(GameEntity game, Map<String, Integer> roundPoints) {
        Map<String, Integer> updatedPlayerToOverallScoreMap = new HashMap<>();
        if(game != null){
            Map<String, Integer> oldPlayerToOverallScoreMap = game.getPlayerToOverallScoreMap();
            for (var entry : roundPoints.entrySet()) {
                Integer lastOverallScoreValueForPlayer = oldPlayerToOverallScoreMap.get(entry.getKey());
                updatedPlayerToOverallScoreMap.put(entry.getKey(),entry.getValue() + lastOverallScoreValueForPlayer);
            }
            System.out.println("updated playerToOverall: "+ updatedPlayerToOverallScoreMap);
            game.setPlayerToOverallScoreMap(updatedPlayerToOverallScoreMap);
            gameRepository.save(game);
        }

        return updatedPlayerToOverallScoreMap;
    }

    public void updateStatisticsForPlayer(List<RankingDTO> rankingList) {
        for (RankingDTO rankingDTO : rankingList) {
            UUID userID = userDetailService.getUserIDByName(rankingDTO.getGameUserDTO().getBenutzername());
            int statisticEntryCount = gameStatisticRepository.countByUserID(userID);
            gameStatisticRepository.save(new GameStatisticEntity(userID,statisticEntryCount + 1, new Date(),rankingList.size(),rankingDTO.getScore(),rankingDTO.getRank()));
        }
    }

    public boolean checkAllUserGuessedWord(String gameId) {
        GameEntity game = gameRepository.findById(gameId).get();
        return countPlayersWithoudDrawer(game) == game.getActiveRound().getRightGuessedUser().size();
    }

    private int countPlayersWithoudDrawer(GameEntity game) {
        return game.getPlayers().size() - 1;
    }
}
