package com.montagsmaler.backend.game;

import com.montagsmaler.backend.Greeting;
import com.montagsmaler.backend.actionHandling.actionInput.Action;
import com.montagsmaler.backend.actionHandling.actionResponse.ActionResponse;
import com.montagsmaler.backend.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.actionHandling.actionStrategies.ActionStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import com.montagsmaler.backend.Message;

import javax.annotation.Resource;
import java.util.Optional;

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

    @PostMapping(value="/backend/structure/{gameId}")
    public Optional<ActionResponse> structureTest(@RequestBody Action action, @PathVariable String gameId) {
        ActionStrategy strategy = strategyFactory.findActionStrategyByActionName(action.getActionType());
        action.setGameId(gameId);
        Optional<ActionResponse> actionResponse = strategy.executeAction(action);

        return actionResponse;
    }

  /*  @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws InterruptedException {
        Thread.sleep(1000); // simulated delay
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        System.out.println("habikuku");
        //this.template.convertAndSend("yay");
        return new Greeting("Hello" + message.getName());
    }*/

    @MessageMapping("/game/{gameId}")
    @SendTo("/updates/{gameId}")
    public Greeting gameSpecificGreeting(@DestinationVariable String gameId, Message message) throws InterruptedException {
        Thread.sleep(1000); // simulated delay
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        System.out.println("habikuku");
        //this.template.convertAndSend("yay");
        return new Greeting("(Game "+gameId+ " :Hello" + message.getName());
    }

    @MessageMapping("/game/{fleetId}/driver/{driverId}")
    @SendTo("/app/fleet/{fleetId}")
    public String simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
        return "Response: " + fleetId + driverId;
    }


}
