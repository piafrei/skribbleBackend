package com.montagsmaler.backend.game.canvas;

public enum Drawcolor {
    BLAU("#2E9AFE"),GRUEN("#74DF00"),ROT("#FE2E2E"),GELB("##FFFF00"),ORANGE("#FF8000"),SCHWARZ("#000000"),WEISS("#FFFFFF");

    String hexcode;

    Drawcolor(String hexcode) {
        this.hexcode = hexcode;
    }

    public String getHexcode() {
        return hexcode;
    }

    public static Drawcolor fromString(String hexcode) {
        for (Drawcolor drawcolor : Drawcolor.values()) {
            if (drawcolor.hexcode.equalsIgnoreCase(hexcode)) {
                return drawcolor;
            }
        }
        System.out.println("Invalid draw color " + hexcode + ". Will return default");
        return Drawcolor.SCHWARZ;
    }

}
