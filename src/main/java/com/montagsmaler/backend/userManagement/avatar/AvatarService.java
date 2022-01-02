package com.montagsmaler.backend.userManagement.avatar;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AvatarService {

    public Map<String,String> getAvatarToImageMap(String baseImagePath){
        Map<String, String> avatarToImageMap = new HashMap<>();
        for (AvatarToImageConfig config : AvatarToImageConfig.values()) {
            avatarToImageMap.put(config.name(), baseImagePath + config.avatarImage);
        }
        return avatarToImageMap;
    }
}
