package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.canvas.Canvas;
import com.montagsmaler.backend.game.canvas.CanvasRepository;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
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
            GameEntity game = gameEntity.get();
            game.getActiveRound().addRightGuessedUser(username);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    Gameround startRound(GameEntity game, int roundNumber, String player) {
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
        GameEntity game = gameRepository.findById(gameId).get();
        return countPlayersWithoudDrawer(game) == game.getActiveRound().getRightGuessedUser().size();
    }

    private int countPlayersWithoudDrawer(GameEntity game) {
        return game.getPlayers().size() - 1;
    }

    public List<GameEntity> getGameIDByUser(String username) {
        return gameRepository.findByPlayerToOverallScoreMapIsContaining(username);
    }

    void deleteById(String gameId) {
        gameRepository.deleteById(gameId);
    }

    public String removeUserFromGame(String gameID, String userName) {
        Optional<GameEntity> gameById = getGameById(gameID);
        if(gameById.isPresent()){
            GameEntity gameEntity = gameById.get();
            String host = gameEntity.removePlayerFromGame(userName);
            gameRepository.save(gameEntity);
            return host;
        }

        return "";
    }

    Map<String, Integer> parseRoundPoints(Set<String> players, Gameround gameround) {
        Map<String, Integer> roundPoints = new HashMap<String, Integer>();
        List<String> rightGuessedUser = gameround.getRightGuessedUser();
        players.forEach(player -> {
            if(gameround.getDrawer().equals(player)){
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
}
