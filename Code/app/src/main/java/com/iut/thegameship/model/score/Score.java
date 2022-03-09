package com.iut.thegameship.model.score;

import java.io.Serializable;

public class Score implements Serializable {
    private String pseudo;
    private float timeSec;

    public Score(String pseudo, float timeSec){
        this.pseudo=pseudo;
        this.timeSec=timeSec;
    }

    public float getTimeSec() {
        return timeSec;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setTimeSec(float timeSec) {
        this.timeSec = timeSec;
    }

}
