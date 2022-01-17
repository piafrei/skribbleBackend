package com.montagsmaler.backend.userManagement.avatar;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class AvatarService {

    public Map<String,String> getAvatarToImageMap(String baseImagePath){
        Map<String, String> avatarToImageMap = new HashMap<>();
        for (AvatarToImageConfig config : AvatarToImageConfig.values()) {
            avatarToImageMap.put(config.name(), baseImagePath + config.avatarImage);
        }
        return avatarToImageMap;
    }

    public Optional<AvatarToImageConfig> getAvatarConfig(String name){
        try {
            AvatarToImageConfig avatarToImageConfig = AvatarToImageConfig.valueOf(name);
            return Optional.of(avatarToImageConfig);
        } catch (Exception e) {
            System.out.println("Invalid avatar name");
            return Optional.empty();
        }
    }

    public Avatar getAvatar(String name){
        if(name != null){
            try {
                AvatarToImageConfig avatarToImageConfig = AvatarToImageConfig.valueOf(name);
                return new Avatar(avatarToImageConfig.name(),  avatarToImageConfig.getAvatarImage());
            } catch (Exception e) {
                System.out.println("Invalid avatar name");
            }
        }
        return getDefaultAvatar();
    }

    public Avatar getDefaultAvatar(){
        Avatar avatar = new Avatar();
        avatar.setToDefaults();
        return avatar;
    }
}
