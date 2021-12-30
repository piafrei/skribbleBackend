package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionInput.implementation.StartGameAction;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.RoundStatisticActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.implementation.StartGameActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    private ActionStrategyFactory strategyFactory;
    @Autowired
    private SimpMessagingTemplate template;
    @Resource
    private GameService gameService;

    @RequestMapping(value="/backend/sayhello")
    public String sayHello() {
        return "Hi!";
    }

    @PostMapping(value="/backend/game")
    public String createGame() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String user = authentication.getName();
        String user = "maia";

        return gameService.createNewGame(user);
    }

    @PostMapping(value="/backend/structure/{gameId}")
    public Optional<ActionResponse> structureTest(@RequestBody Action action, @PathVariable String gameId) {
        ActionStrategy strategy = strategyFactory.findActionStrategyByActionName(action.getActionType());
        action.setGameId(gameId);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //action.setUsername(authentication.getName());
        action.setUsername("maia");

        return strategy.executeAction(action);
    }

    public void sendScheduledUpdate(String gameId, ActionResponse actionResponse)
    {
        template.convertAndSend("/updates/" + gameId, actionResponse);
    }

    public void sendToSpecificUser(String gameId, String userId, ActionResponse actionResponse)
    {
        template.convertAndSend("/updates/" + gameId + "/user/" + userId, actionResponse);
    }

    @MessageMapping("/game/{gameId}")
    @SendTo("/updates/{gameId}")
    public Optional<ActionResponse> gameSpecificGreeting(@DestinationVariable String gameId, Action action) {
        ActionStrategy strategy = strategyFactory.findActionStrategyByActionName(action.getActionType());
        action.setGameId(gameId);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //action.setUsername(authentication.getName());
        action.setUsername("pia");

        return strategy.executeAction(action);
    }

    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {

        var imgFile = new ClassPathResource("image/burger.jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
