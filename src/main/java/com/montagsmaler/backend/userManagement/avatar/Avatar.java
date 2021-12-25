package com.montagsmaler.backend.userManagement.avatar;

public class Avatar {
    private String farbe;
    private Augentyp augen;
    private Mundtyp mund;

    public Avatar() {
    }

    public Avatar(String farbe, Augentyp augen, Mundtyp mund) {
        this.farbe = farbe;
        this.augen = augen;
        this.mund = mund;
    }

    public void setToDefaults(){
        farbe = "schwarz";
        augen = Augentyp.NORMAL;
        mund = Mundtyp.GLUECKLICH;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public Augentyp getAugen() {
        return augen;
    }

    public void setAugen(Augentyp augen) {
        this.augen = augen;
    }

    public Mundtyp getMund() {
        return mund;
    }

    public void setMund(Mundtyp mund) {
        this.mund = mund;
    }
}
