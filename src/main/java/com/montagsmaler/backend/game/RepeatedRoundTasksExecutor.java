package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.gameEvents.*;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.game.datatransferObjects.RankingDTO;
import com.montagsmaler.backend.game.gameEvents.Observer;
import com.montagsmaler.backend.game.messaging.RoundMessageService;
import com.montagsmaler.backend.helper.SpringContext;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;

import javax.annotation.Resource;
import java.util.*;

public class RepeatedRoundTasksExecutor implements Runnable, Observer {
    private static final int ONE_MINUTE_IN_MILLI_SEC = 60000;
    private static final int FIVE_SECONDS_IN_MILLI_SEC = 6000;

    @Resource
    private RoundMessageService messageService;
    @Resource
    private GameService gameService;
    @Resource
    private EventHandler eventHandler;
    @Resource
    private UserDetailServiceImpl userDetailService;

    private String gameId;
    private List<String> playersList;
    private String currentDrawer;

    public RepeatedRoundTasksExecutor(String gameId) {
        this.messageService = SpringContext.getBean(RoundMessageService.class);
        this.gameService = SpringContext.getBean(GameService.class);
        this.userDetailService = SpringContext.getBean(UserDetailServiceImpl.class);
        this.eventHandler = SpringContext.getBean(EventHandler.class);
        this.gameId = gameId;

        this.eventHandler.attach(this);
    }

    public void run() {
        try {
            Optional<GameEntity> initialGameEntity = gameService.getGameById(gameId);

            if (initialGameEntity.isPresent()) {
                GameEntity game = initialGameEntity.get();
                Set<String> players = game.getPlayers();
                playersList = new ArrayList<>(players);
                int currentRound = 1;

                while (currentRound <= game.getRounds()) {
                    //falls ein user disconnected, verändert sich die for loop dynamisch
                    for (int i = 0; i < playersList.size(); i++) {
                        currentDrawer = playersList.get(i);
                        startRoundForPlayer(game, currentRound);
                    }

                    currentRound++;
                }
                Thread.sleep(FIVE_SECONDS_IN_MILLI_SEC);

                GameEntity gameResult = gameService.getGameById(gameId).get();
                List<RankingDTO> rankingDTOS = gameService.parseRankingList(gameResult.getPlayerToOverallScoreMap());
                gameService.updateStatisticsForPlayer(rankingDTOS);
                messageService.sendGameEndedMessage(gameId,rankingDTOS);

                gameFinishedCleanup();
            }
        } catch (Exception e) {
            System.out.println("There was a exception! " + e.getCause() + e.getMessage());
        }
    }

    private void startRoundForPlayer(GameEntity game, int currentRound) throws InterruptedException {
        GameEntity preRoundUpdatedGame = gameService.getGameById(gameId).get();
        GameUserDTO drawer = userDetailService.getGameUserByName(currentDrawer).get();
        Gameround gameround = gameService.startRound(preRoundUpdatedGame, currentRound, currentDrawer);

        messageService.sendWordToDrawToCurrentDrawer(gameId,drawer, gameround);
        messageService.sendNewRoundInformationToAllUser(gameId,drawer, gameround);

        waitUntilAllUserGuessRightOrTimeIsUp();

        GameEntity updatedGame = gameService.getGameById(gameId).get();
        Map<String, Integer> roundPoints = getRoundPoints(updatedGame);

        messageService.sendRoundStatistics(game, gameround,
                updatedGame, roundPoints,
                gameService.parseRankingList(getOverallPoints(updatedGame, roundPoints)));
        Thread.sleep(FIVE_SECONDS_IN_MILLI_SEC);
    }

    @Override
    public void update(GameEvent event) {
        if (event instanceof AllUserGuessedWordEvent) {
            continuePausedThreadExecuter();
        }
        if (event instanceof UserDisconnectedEvent) {
            String userId = ((UserDisconnectedEvent) event).getUserId();
            this.playersList.remove(userId);
            if (currentDrawer.equals(userId)) {
                continuePausedThreadExecuter();
            }
        }
    }


    @Override
    public String getGameObserverIdentifier() {
        return gameId;
    }

    private Map<String, Integer> getOverallPoints(GameEntity updatedGame, Map<String, Integer> roundPoints) {
        return gameService.updateOverallPoints(updatedGame, roundPoints);
    }

    private Map<String, Integer> getRoundPoints(GameEntity updatedGame) {
        return gameService.parseRoundPoints(updatedGame.getPlayers(), updatedGame.getActiveRound());
    }


    private void waitUntilAllUserGuessRightOrTimeIsUp() {
        try {
            synchronized (this) {
                //wait until all user guessed right or time is up
                this.wait(ONE_MINUTE_IN_MILLI_SEC);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameFinishedCleanup() {
        this.eventHandler.detach(this);
        this.gameService.deleteById(gameId);
    }

    private void continuePausedThreadExecuter() {
        synchronized (this) {
            this.notifyAll();
        }
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
