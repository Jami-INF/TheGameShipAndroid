package com.iut.thegameship.model.score;

import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable {
    private String pseudo;
    private double scoregame;
    private int dificulty;

    public Score(String pseudo, double timeSec, int dificulty){
        this.pseudo=pseudo;
        this.scoregame=timeSec;
        this.dificulty=dificulty;
    }

    public double getTimeSec() {
        return scoregame;
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
        this.scoregame = timeSec;
    }

    public void setDificulty(int dificulty){
        this.dificulty = dificulty;
    }

    public static Comparator<Score> ComparatorScoreGame = new Comparator<Score>() {

        @Override
        public int compare(Score score, Score t1) {
            return (int) (t1.getTimeSec() - score.getTimeSec());
        }

    };

}
