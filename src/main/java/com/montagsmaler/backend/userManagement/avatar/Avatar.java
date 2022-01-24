package com.montagsmaler.backend.userManagement.avatar;

import static com.montagsmaler.backend.userManagement.avatar.AvatarController.AVATAR_ROOT_MAPPING;
import static com.montagsmaler.backend.userManagement.avatar.AvatarController.PATH_TO_IMAGE_FOLDER;

public class Avatar {

    private String name;
    private String imagePath;

    Avatar() {
    }

    Avatar(String name, String imagePath) {
        this.name = name;
        this.imagePath = AVATAR_ROOT_MAPPING + PATH_TO_IMAGE_FOLDER + imagePath;
    }

    void setToDefaults(){
        name = AvatarToImageConfig.ANANAS.name();
        imagePath = AVATAR_ROOT_MAPPING + PATH_TO_IMAGE_FOLDER + AvatarToImageConfig.ANANAS.getAvatarImage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
