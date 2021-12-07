package com.montagsmaler.backend.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import sun.java2d.pipe.SpanShapeRenderer;

@RestController
public class GameController {

    @RequestMapping(value="/sayhello")
    public String sayHello() {
        return "Hi!";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/game/{gameid}")
    @SendTo("/channel/game/{gameid}}")
    public Greeting simple(@DestinationVariable String gameid, ActionMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape("heo!"));
    }
}
