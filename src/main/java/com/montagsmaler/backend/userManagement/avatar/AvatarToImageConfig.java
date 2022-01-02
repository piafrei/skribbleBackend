package com.montagsmaler.backend.userManagement.avatar;

public enum AvatarToImageConfig {
    ALIEN("alien.jpg"),
    ANANAS("ananas.jpg"),
    BURGER("burger.jpg"),
    DONUT("donut.jpg"),
    EINHORN("einhorn.jpg"),
    ERDBEERE("erdbeere.jpg"),
    FISCH("fisch.jpg"),
    KUERBIS("kuerbis.jpg"),
    MUMIE("mumie.jpg"),
    PINSEL("pinsel.jpg"),
    PIRAT("pirat.jpg"),
    PIRATMITBART("piratMitBart.jpg"),
    PIZZA("pizza.jpg"),
    RITTER("ritter.jpg"),
    SAHNEMAN("sahneman.jpg"),
    SCHAEDEL("schaedel.jpg"),
    SCHWEINCHEN("schweinchen.jpg"),
    WIKINGER("wikinger.jpg"),
    ZOMBIE("zombie.jpg"),
    ZOMBIEMITSCHAEDEL("zombieMitSchaedel.jpg");

    String avatarImage;

    AvatarToImageConfig(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getAvatarImage() {
        return avatarImage;
    }
}
