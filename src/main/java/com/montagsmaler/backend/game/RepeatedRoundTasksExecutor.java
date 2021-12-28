package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.helper.SpringContext;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.DrawerWordActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.GameEndedRankingActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.NewRoundActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.RoundStatisticActionResponse;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;

import java.util.*;

public class RepeatedRoundTasksExecutor implements Runnable {
    private static final int POINTS_FOR_RIGHT_GUESS = 30;
    public static final int ONE_MINUTE_IN_MILLI_SEC = 60000;
    public static final int POINTS_FOR_DRAWER_PER_RIGHT_GUESS = 5;

    GameController gameController;
    GameService gameService;
    UserDetailServiceImpl userDetailService;
    String gameId;
    int rounds;

    public RepeatedRoundTasksExecutor(String gameId, int rounds) {
        this.gameController = SpringContext.getBean(GameController.class);
        this.gameService = SpringContext.getBean(GameService.class);
        this.userDetailService = SpringContext.getBean(UserDetailServiceImpl.class);
        this.gameId = gameId;
        this.rounds = rounds;
    }

    public void run()
    {
        try
        {
            Optional<GameEntity> initialGameEntity = gameService.getGameById(gameId);
            GameEntity updatedGame = null;

            if(initialGameEntity.isPresent()){
                GameEntity game = initialGameEntity.get();
                int currentRound = 1;

                while (currentRound <= game.getRounds()){
                    for (String currentDrawer: game.getPlayers()) {
                        GameUserDTO drawer = userDetailService.getGameUserByName(currentDrawer).get();
                        Gameround gameround = gameService.startRound(game, currentRound, currentDrawer);
                        gameController.sendToSpecificUser(game.getGameId(), drawer.getBenutzername(), new DrawerWordActionResponse(gameround.getActiveWord()));
                        gameController.sendScheduledUpdate(game.getGameId(), new NewRoundActionResponse(drawer, gameround.getActiveWord().getWordLenth(), gameround.getRoundNumber()));

                        System.out.println("lets wait a min");
                        java.lang.Thread.sleep(ONE_MINUTE_IN_MILLI_SEC);
                        System.out.println("okay lets continue");

                        updatedGame = gameService.getGameById(gameId).get();
                        Map<String, Integer> roundPoints = parseRoundPoints(gameround);
                        gameService.updateOverallPoints(gameId, roundPoints);
                        gameController.sendScheduledUpdate(game.getGameId(), new RoundStatisticActionResponse(updatedGame.getActiveRound().getRoundNumber(),gameround.getActiveWord().getValue(), roundPoints, parsePlayerToScoreMap(updatedGame.getPlayerToOverallScoreMap())));
                    }
                    currentRound++;
                }
                updatedGame = gameService.getGameById(gameId).get();
                gameController.sendScheduledUpdate(gameId,new GameEndedRankingActionResponse(parsePlayerToScoreMap(updatedGame.getPlayerToOverallScoreMap())));
            }
        }
        catch (Exception e)
        {
            System.out.println("There was a exception! " + e);
        }
    }

    private Map<String, Integer> parseRoundPoints(Gameround gameround) {
        Map<String, Integer> roundPoints = new HashMap<String, Integer>();
        List<String> rightGuessedUser = gameround.getRightGuessedUser();
        rightGuessedUser.forEach(rigthGuessedUser -> roundPoints.put(rigthGuessedUser, POINTS_FOR_RIGHT_GUESS));
        int sizeOfRightGuesses = rightGuessedUser.size();
        roundPoints.put(gameround.getDrawer(), sizeOfRightGuesses * POINTS_FOR_DRAWER_PER_RIGHT_GUESS);
        return roundPoints;
    }

    private List<RankingDTO> parsePlayerToScoreMap(Map<String, Integer> playerToScoreMap) {
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
