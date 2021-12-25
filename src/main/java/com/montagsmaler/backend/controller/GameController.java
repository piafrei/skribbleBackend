package com.montagsmaler.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.annotation.Resource;

@RestController
public class GameController {
    @Autowired
    private ActionStrategyFactory strategyFactory;
    @Resource
    private GameService gameService;

    private SimpMessagingTemplate template;

    @RequestMapping(value="/backend/sayhello")
    public String sayHello() {
        return "Hi!";
    }

    @PostMapping(value="/backend/game")
    public String createGame() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return gameService.createNewGame(user);
    }

    @PostMapping(value="/backend/structure")
    public Action structureTest(@RequestBody Action action) {
//        Boolean isAChat = action instanceof ChatAction;
//        Boolean isADraw = action instanceof DrawAction;
//        Boolean isAActionParam = action != null;

        ActionStrategy strategy = strategyFactory.findActionStrategyByActionName(action.getActionType());
        strategy.doStuff();

        return action;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Greeting greeting(String message) {
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        System.out.println("habikuku");
        //this.template.convertAndSend("yay");
        return new Greeting("Hello" + message);
    }

    @MessageMapping("/game/{fleetId}/driver/{driverId}")
    @SendTo("/app/fleet/{fleetId}")
    public String simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
        return "Response: " + fleetId + driverId;
    }


}
