package com.montagsmaler.backend.configuration.websocket;

import com.montagsmaler.backend.game.GameService;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.UserDisconnectedActionResponse;
import com.montagsmaler.backend.game.gameEvents.EventHandler;
import com.montagsmaler.backend.game.gameEvents.UserDisconnectedEvent;
import com.montagsmaler.backend.game.messaging.StompTemplateMessageService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Optional;

@Component
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {
    @Resource
    private EventHandler eventHandler;

    @Resource
    private GameService gameService;

    @Resource
    private StompTemplateMessageService messageService;

    @Override
    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent event) {
        Principal user = event.getUser();
        if(user != null){
            String userName = user.getName();
            System.out.println("All Connection closed for user: " + userName);
            Optional<String> gameID = gameService.getGameIDByUser(userName);
            gameID.ifPresent(id -> {
                String host = gameService.removeUserFromGame(id, userName);
                eventHandler.notifyUpdate(new UserDisconnectedEvent(id, userName, host));
                messageService.sendScheduledUpdate(id,new UserDisconnectedActionResponse(userName, host));
            });
        }
    }
}
