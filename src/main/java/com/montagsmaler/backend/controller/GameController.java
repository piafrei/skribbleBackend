package com.montagsmaler.backend.controller;

import com.montagsmaler.backend.controller.ActionInput.ChatAction;
import com.montagsmaler.backend.controller.ActionInput.DrawAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@RestController
public class GameController {
    @Autowired
    private ActionStrategyFactory strategyFactory;

    private SimpMessagingTemplate template;

    @RequestMapping(value="/backend/sayhello")
    public String sayHello() {
        return "Hi!";
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
    public void greeting(String message) {
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        System.out.println("habikuku");
        this.template.convertAndSend("yay");
        //return new Greeting("Hello" + message);
    }

    @MessageMapping("/game/{fleetId}/driver/{driverId}")
    @SendTo("/app/fleet/{fleetId}")
    public String simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
        return "Response: " + fleetId + driverId;
    }
}
