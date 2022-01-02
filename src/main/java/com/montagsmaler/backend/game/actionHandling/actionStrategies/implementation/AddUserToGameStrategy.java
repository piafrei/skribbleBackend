package com.montagsmaler.backend.game.actionHandling.actionStrategies.implementation;

import com.montagsmaler.backend.game.canvas.CanvasDTO;
import com.montagsmaler.backend.game.canvas.CanvasService;
import com.montagsmaler.backend.game.datatransferObjects.GameIntermediateStatusDTO;
import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.AddUserToGameAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.UserJoinedActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyName;
import com.montagsmaler.backend.game.GameEntity;
import com.montagsmaler.backend.game.GameService;
import com.montagsmaler.backend.game.datatransferObjects.GameUserDTO;
import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import com.montagsmaler.backend.userManagement.UserEntity;
import com.montagsmaler.backend.userManagement.avatar.AvatarService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class AddUserToGameStrategy implements ActionStrategy {
    @Resource
    GameService gameService;

    @Resource
    CanvasService canvasService;

    @Resource
    UserDetailServiceImpl userDetailService;

    @Resource
    AvatarService avatarService;

    @Override
    public Optional<ActionResponse> executeAction(Action action) {
        System.out.print("inside addUserToGame action");

        if (!(action instanceof AddUserToGameAction)){
            System.out.println("Wrong action type");
            return Optional.empty();
        }
        AddUserToGameAction addUserToGameAction = (AddUserToGameAction) action;
        String username = addUserToGameAction.getUsername();
        UserEntity userEntity = userDetailService.getUserEntityByName(username);
        if(userEntity != null){
            Optional<GameEntity> gameEntity = gameService.addUserToGame(addUserToGameAction.getGameId(), username);
            if(gameEntity.isPresent()){
                GameUserDTO user = new GameUserDTO(userEntity, avatarService.getAvatar(userEntity.getAvatar()));
                GameEntity game = gameEntity.get();
                CanvasDTO canvas = canvasService.getCanvasDTO(game).get();
                return Optional.of(new UserJoinedActionResponse(user, new GameIntermediateStatusDTO(game, canvas, gameService.parsePlayerToScoreMap(game.getPlayerToOverallScoreMap()), game.getRounds()), game.isGameRunning()));
            }
        }
        return Optional.empty();
    }

    @Override
    public ActionStrategyName getStrategyName() {
        return ActionStrategyName.AddUserToGameStrategy;
    }
}
