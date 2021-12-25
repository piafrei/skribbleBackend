package com.montagsmaler.backend.controller;

import com.montagsmaler.backend.UserManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class GameService {
    @Resource
    UserDetailServiceImpl userDetailsService;

    @Resource
    GameRepository gameRepository;

    public String createNewGame(String createrUserName) {
        UUID userIDByName = userDetailsService.getUserIDByName(createrUserName);
        GameEntity game = new GameEntity();
        game.addPlayer(userIDByName);
        game.setHost(userIDByName);
        gameRepository.save(game);
        return game.getGameId();
    }
}
