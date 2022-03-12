package com.iut.thegameship.model.score;

import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable {
    private String pseudo;
    private double timeSec;
    private int dificulty;

    public Score(String pseudo, double timeSec, int dificulty){
        this.pseudo=pseudo;
        this.timeSec=timeSec;
        this.dificulty=dificulty;
    }

    public double getTimeSec() {
        return timeSec;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getDificulty(){
        return dificulty;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setTimeSec(double timeSec) {
        this.timeSec = timeSec;
    }

    public void setDificulty(int dificulty){
        this.dificulty = dificulty;
    }

    public static Comparator<Score> ComparatorDificulty = new Comparator<Score>() {

        @Override
        public int compare(Score score, Score t1) {
            return score.getDificulty() - t1.getDificulty();
        }

    };

}
