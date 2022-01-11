package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.helper.SpringContext;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.DrawerWordActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.GameEndedRankingActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.NewRoundActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.RoundStatisticActionResponse;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepeatedRoundTasksExecutor implements Runnable, ApplicationListener<AllUserGuessedWordEvent> {
    private static final int POINTS_FOR_RIGHT_GUESS = 30;
    public static final int ONE_MINUTE_IN_MILLI_SEC = 60000;
    public static final int FIVE_SECONDS_IN_MILLI_SEC = 6000;
    public static final int POINTS_FOR_DRAWER_PER_RIGHT_GUESS = 5;

    GameController gameController;
    GameService gameService;
    UserDetailServiceImpl userDetailService;
    String gameId;
    int rounds;
    private static final Object foo = new Object();
    boolean allPlayersGuessedRight = false;

    public RepeatedRoundTasksExecutor(){
    }

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

            if(initialGameEntity.isPresent()){
                GameEntity game = initialGameEntity.get();
                int currentRound = 1;

                while (currentRound <= game.getRounds()){
                    for (String currentDrawer: game.getPlayers()) {
                        GameEntity preRoundUpdatedGame = gameService.getGameById(gameId).get();
                        GameUserDTO drawer = userDetailService.getGameUserByName(currentDrawer).get();
                        Gameround gameround = gameService.startRound(preRoundUpdatedGame, currentRound, currentDrawer);
                        gameController.sendToSpecificUser(game.getGameId(), drawer.getBenutzername(), new DrawerWordActionResponse(gameround.getActiveWord()));
                        gameController.sendScheduledUpdate(game.getGameId(), new NewRoundActionResponse(drawer, gameround.getActiveWord().getWordLenth(), gameround.getRoundNumber()));

                        System.out.println("lets wait a min");
                         /*   try {
                                synchronized (foo){
                                    while (!allPlayersGuessedRight){
                                        System.out.println("okay lets wait in sync");
                                        foo.wait();
                                    }
                                    //continue
                                    System.out.println("okay lets continue in sync");
                                }
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }*/

                        try {
                            Thread.sleep(ONE_MINUTE_IN_MILLI_SEC);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //System.out.println("okay lets continue");

                        GameEntity updatedGame = gameService.getGameById(gameId).get();
                        Map<String, Integer> roundPoints = parseRoundPoints(updatedGame.getPlayers(),updatedGame.getActiveRound());
                        Map<String, Integer> overallPoints = gameService.updateOverallPoints(updatedGame, roundPoints);
                        gameController.sendScheduledUpdate(game.getGameId(), new RoundStatisticActionResponse(updatedGame.getActiveRound().getRoundNumber(),gameround.getActiveWord().getValue(), roundPoints, parsePlayerToScoreMap(overallPoints)));
                        Thread.sleep(FIVE_SECONDS_IN_MILLI_SEC);
                    }
                    currentRound++;
                }
                Thread.sleep(FIVE_SECONDS_IN_MILLI_SEC);
                GameEntity gameResult = gameService.getGameById(gameId).get();
                List<RankingDTO> rankingDTOS = parsePlayerToScoreMap(gameResult.getPlayerToOverallScoreMap());
                gameService.updateStatisticsForPlayer(rankingDTOS);
                gameController.sendScheduledUpdate(gameId,new GameEndedRankingActionResponse(rankingDTOS));
            }
        }
        catch (Exception e)
        {
            System.out.println("There was a exception! " + e.getStackTrace() + e.getCause() + e.getMessage());
        }
    }

    @Override
    public void onApplicationEvent(AllUserGuessedWordEvent event) {
        System.out.println("Received spring custom event - " + event.getGameId());
        synchronized (foo){
            allPlayersGuessedRight = true;
            foo.notify();
        }
    }

    private Map<String, Integer> parseRoundPoints(Set<String> players, Gameround gameround) {
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
