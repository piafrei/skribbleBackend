package com.montagsmaler.backend.game;

import com.montagsmaler.backend.game.actionHandling.actionInput.Action;
import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategy;
import com.montagsmaler.backend.game.actionHandling.actionStrategies.ActionStrategyFactory;
import com.montagsmaler.backend.userManagement.avatar.AvatarService;
import com.montagsmaler.backend.userManagement.avatar.AvatarToImageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GameController {
    public static final String PATH_TO_IMAGE_FOLDER = "image/";
    public static final String AVATAR_ROOT_MAPPING = "/avatar/";
    @Autowired
    private ActionStrategyFactory strategyFactory;
    @Autowired
    private SimpMessagingTemplate template;
    @Resource
    private GameService gameService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Resource
    private AvatarService avatarService;
    @Resource
    private GameStatisticService gameStatisticService;


    @RequestMapping(value="/backend/sayhello")
    public String sayHello() {
        return "Hi!";
    }

    @RequestMapping(value="/checkAuth")
    public ResponseEntity checkAuthState() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/gameStatistic")
    public List<GameStatisticEntity> getGameStatistic() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return gameStatisticService.getGameStatisticsForUser(user);
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

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //action.setUsername(authentication.getName());
        action.setUsername("Maia");
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        action.setUsername("Maia");
        //action.setUsername(authentication.getName());

        return strategy.executeAction(action);
    }

    @GetMapping(value = "/avatar")
    public Map<String,String> getAllAvatars(){
        return avatarService.getAvatarToImageMap(AVATAR_ROOT_MAPPING + PATH_TO_IMAGE_FOLDER);
    }

    @RequestMapping(value = AVATAR_ROOT_MAPPING +PATH_TO_IMAGE_FOLDER+"{imageName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageName)  {
        try {
            //AvatarToImageConfig avatarToImageConfig = AvatarToImageConfig.valueOf(name);
            var imgFile = new ClassPathResource(PATH_TO_IMAGE_FOLDER + imageName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }
}
