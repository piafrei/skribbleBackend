package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.canvas.Canvas;
import com.montagsmaler.backend.game.canvas.CanvasRepository;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.gamestatistics.GameStatisticEntity;
import com.montagsmaler.backend.game.gamestatistics.GameStatisticRepository;
import com.montagsmaler.backend.game.wordsToGuess.WordService;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GameService {
    private static final int POINTS_FOR_RIGHT_GUESS = 30;
    private static final int POINTS_FOR_DRAWER_PER_RIGHT_GUESS = 5;

    @Resource
    private GameRepository gameRepository;

    @Resource
    private WordService wordService;

    @Resource
    private UserDetailServiceImpl userDetailService;

    @Resource
    private CanvasRepository canvasRepository;

    @Resource
    private GameStatisticRepository gameStatisticRepository;

    Optional<GameEntity> getGameById(String gameId){
        return gameRepository.findById(gameId);
    }

    String createNewGame(String createrUserName) {
        GameEntity game = initialiseGameEntity(createrUserName);
        gameRepository.save(game);
        String gameId = game.getGameId();
        canvasRepository.save(initialiseCanvasWithGameId(gameId));
        return gameId;
    }

    public Optional<GameEntity> addUserToGame(String gameId, String username) {
        Optional<GameEntity> gameEntity = getGameById(gameId);
        if(gameEntity.isPresent()){
            GameEntity game = gameEntity.get();
            game.addPlayer(username);
            gameRepository.save(game);
            return gameEntity;
        }
        return Optional.empty();
    }

    public boolean checkIsWordCorrect(String gameId, String message, String username) {
        Optional<GameEntity> gameEntity = getGameById(gameId);
        if(gameEntity.isPresent()){
            GameEntity game = gameEntity.get();
            Gameround activeRound = game.getActiveRound();
            if(activeRound != null && guessedWordMatchesActiveWord(message, activeRound)){
                if(activeRound.userIsAllowedToGuess(username)){
                    activeRound.addRightGuessedUser(username);
                    gameRepository.save(game);
                }
                return true;
            }

        }
        return false;
    }

    public List<GameEntity> getGameIDByUser(String username) {
        return gameRepository.findByPlayerToOverallScoreMapIsContaining(username);
    }

    void deleteById(String gameId) {
        gameRepository.deleteById(gameId);
    }

    public String removeUserFromGame(String gameID, String userName) {
        String newHost = "";
        Optional<GameEntity> gameById = getGameById(gameID);
        if(gameById.isPresent()){
            GameEntity gameEntity = gameById.get();
            newHost = gameEntity.removePlayerFromGame(userName);
            gameRepository.save(gameEntity);
            return newHost;
        }

        return newHost;
    }

    Map<String, Integer> parseRoundPoints(Set<String> players, Gameround gameround) {
        Map<String, Integer> roundPoints = new HashMap<>();
        List<String> rightGuessedUser = gameround.getRightGuessedUser();
        players.forEach(player -> {
            if(isPlayerDrawer(gameround, player)){
                int sizeOfRightGuesses = rightGuessedUser.size();
                roundPoints.put(player, sizeOfRightGuesses * POINTS_FOR_DRAWER_PER_RIGHT_GUESS);
            }
            else if(rightGuessedUser.contains(player)){
                roundPoints.put(player, POINTS_FOR_RIGHT_GUESS);
            } else {
                roundPoints.put(player, 0);
            }
        });
        return roundPoints;
    }


    List<RankingDTO> parseRankingList(Map<String, Integer> playerToScoreMap) {
        List<RankingDTO> rankingDTOList = new ArrayList<>();

        final int[] rank = {1};
        playerToScoreMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> {
                    rankingDTOList.add(new RankingDTO(rank[0],userDetailService.getGameUserByName(entry.getKey()).get(),entry.getValue()));
                    rank[0]++;
                });

        return rankingDTOList;
    }

    Gameround startRound(GameEntity game, int roundNumber, String player) {
        Gameround activeRound = initialiseGameround(roundNumber, player);
        game.setActiveRound(activeRound);
        gameRepository.save(game);
        return activeRound;
    }


    Map<String, Integer> updateOverallPoints(GameEntity game, Map<String, Integer> roundPoints) {
        Map<String, Integer> updatedPlayerToOverallScoreMap = new HashMap<>();
        if(game != null){
            Map<String, Integer> oldPlayerToOverallScoreMap = game.getPlayerToOverallScoreMap();
            for (var entry : roundPoints.entrySet()) {
                Integer lastOverallScoreValueForPlayer = oldPlayerToOverallScoreMap.get(entry.getKey());
                updatedPlayerToOverallScoreMap.put(entry.getKey(),entry.getValue() + lastOverallScoreValueForPlayer);
            }
            game.setPlayerToOverallScoreMap(updatedPlayerToOverallScoreMap);
            gameRepository.save(game);
        }

        return updatedPlayerToOverallScoreMap;
    }

    void updateStatisticsForPlayer(List<RankingDTO> rankingList) {
        for (RankingDTO rankingDTO : rankingList) {
            UUID userID = userDetailService.getUserIDByName(rankingDTO.getGameUserDTO().getUsername());
            int statisticEntryCount = gameStatisticRepository.countByUserID(userID);
            gameStatisticRepository.save(new GameStatisticEntity(userID,statisticEntryCount + 1, new Date(),rankingList.size(),rankingDTO.getScore(),rankingDTO.getRank()));
        }
    }

    public boolean checkAllUserGuessedWord(String gameId) {
        GameEntity game = getGameById(gameId).get();
        return countPlayersWithoutDrawer(game) == game.getActiveRound().getRightGuessedUser().size();
    }

    private Gameround initialiseGameround(int roundNumber, String player) {
        Gameround activeRound = new Gameround();
        activeRound.setRoundNumber(roundNumber);
        activeRound.setActiveWord(wordService.getRandomWord());
        activeRound.setDrawer(player);
        return activeRound;
    }

    private int countPlayersWithoutDrawer(GameEntity game) {
        return game.getPlayers().size() - 1;
    }

    private boolean isPlayerDrawer(Gameround gameround, String player) {
        return gameround.getDrawer().equals(player);
    }

    private boolean guessedWordMatchesActiveWord(String message, Gameround activeRound) {
        return activeRound.getActiveWord().getValue().equals(message);
    }

    private GameEntity initialiseGameEntity(String createrUserName) {
        GameEntity game = new GameEntity();
        game.addPlayer(createrUserName);
        game.setHost(createrUserName);
        return game;
    }

    private Canvas initialiseCanvasWithGameId(String gameId) {
        Canvas canvas = new Canvas();
        canvas.setGameId(gameId);
        return canvas;
    }
}
