package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.gameEvents.EventHandler;
import com.montagsmaler.backend.game.gameEvents.Observer;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.gameEvents.AllUserGuessedWordEvent;
import com.montagsmaler.backend.game.gameEvents.GameEvent;
import com.montagsmaler.backend.helper.SpringContext;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.DrawerWordActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.GameEndedRankingActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.NewRoundActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.RoundStatisticActionResponse;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;

import javax.annotation.Resource;
import java.util.*;

public class RepeatedRoundTasksExecutor implements Runnable, Observer {
    private static final int POINTS_FOR_RIGHT_GUESS = 30;
    public static final int ONE_MINUTE_IN_MILLI_SEC = 60000;
    public static final int FIVE_SECONDS_IN_MILLI_SEC = 6000;
    public static final int POINTS_FOR_DRAWER_PER_RIGHT_GUESS = 5;

    @Resource
    private GameController gameController;
    @Resource
    private GameService gameService;
    @Resource
    private EventHandler eventHandler;
    @Resource
    private UserDetailServiceImpl userDetailService;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    String gameId;
    int rounds;

    public RepeatedRoundTasksExecutor(String gameId, int rounds) {
        this.gameController = SpringContext.getBean(GameController.class);
        this.gameService = SpringContext.getBean(GameService.class);
        this.userDetailService = SpringContext.getBean(UserDetailServiceImpl.class);
        this.eventHandler = SpringContext.getBean(EventHandler.class);
        this.gameId = gameId;
        this.rounds = rounds;

        this.eventHandler.attach(this);
    }

    public void run()
    {
        try
        {
        System.out.println(gameId);
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

                    try {
                        synchronized (this){
                            //wait until all user finished or time is up
                            this.wait(ONE_MINUTE_IN_MILLI_SEC);
                        }
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
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
            this.eventHandler.detach(this);
        }
        }
        catch (Exception e)
        {
            System.out.println("There was a exception! " + e.getStackTrace() + e.getCause() + e.getMessage());
        }
    }


    @Override
    public void update(GameEvent event) {
        System.out.println("Subscriber :: " + event.getGameId());
        if(event instanceof AllUserGuessedWordEvent){
            continuePausedThreadExecuter();
        }
    }

    private void continuePausedThreadExecuter() {
        synchronized (this){
            this.notifyAll();
        }
    }

    @Override
    public String getGameObserverIdentifier() {
        return gameId;
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
