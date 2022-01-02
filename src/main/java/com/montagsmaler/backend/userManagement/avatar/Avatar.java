package com.montagsmaler.backend.userManagement.avatar;

import static com.montagsmaler.backend.game.GameController.PATH_TO_IMAGE_FOLDER;

public class Avatar {

    private String name;
    private String imagePath;

    public Avatar() {
    }

    public Avatar(String name, String imagePath) {
        this.name = name;
        this.imagePath = PATH_TO_IMAGE_FOLDER + imagePath;
    }

    public void setToDefaults(){
        name = AvatarToImageConfig.ANANAS.name();
        imagePath = PATH_TO_IMAGE_FOLDER + AvatarToImageConfig.ANANAS.getAvatarImage();
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
