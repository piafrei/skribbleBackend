package com.montagsmaler.backend.game.messaging;

import com.montagsmaler.backend.game.actionHandling.actionResponseDefinition.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class StompTemplateMessageService {
    @Autowired
    private SimpMessagingTemplate template;

    public void sendScheduledUpdate(String gameId, ActionResponse actionResponse)
    {
        template.convertAndSend("/updates/" + gameId, actionResponse);
    }

    public void sendToSpecificUser(String gameId, String userId, ActionResponse actionResponse)
    {
        template.convertAndSend("/updates/" + gameId + "/user/" + userId, actionResponse);
    }
}
