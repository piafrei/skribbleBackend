package com.montagsmaler.backend.controller;

import com.montagsmaler.backend.controller.ActionInput.ChatAction;
import com.montagsmaler.backend.controller.ActionInput.DrawAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@RestController
public class GameController {
    @Autowired
    private ActionStrategyFactory strategyFactory;

    @RequestMapping(value="/sayhello")
    public String sayHello() {
        return "Hi!";
    }

    @PostMapping(value="/structure")
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
    public Greeting greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        return new Greeting("Hello" + message);
    }

/*    @MessageMapping("/game/{gameid}")
    @SendTo("/channel/game/{gameid}}")
    public Greeting simple(@DestinationVariable String gameid, Action message) {
        return new Greeting("Hello, received" + HtmlUtils.htmlEscape("actiontype: " + message.getActionType()));
    }*/
}
