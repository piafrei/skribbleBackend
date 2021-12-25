package com.montagsmaler.backend.game;

import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    @Resource
    UserDetailServiceImpl userDetailsService;

    @Resource
    GameRepository gameRepository;

    @Resource
    UserDetailServiceImpl userDetailService;

    public String createNewGame(String createrUserName) {
        UUID userIDByName = userDetailsService.getUserIDByName(createrUserName);
        GameEntity game = new GameEntity();
        game.addPlayer(userIDByName);
        game.setHost(userIDByName);
        gameRepository.save(game);
        return game.getGameId();
    }

    public void addUserToGame(String gameId, String username) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        gameEntity.ifPresent(game -> {
            game.addPlayer(userDetailService.getUserIDByName(username));
            gameRepository.save(game);
        });
    }
}
