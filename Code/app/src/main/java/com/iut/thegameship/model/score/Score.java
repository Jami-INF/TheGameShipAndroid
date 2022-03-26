package com.iut.thegameship.model.score;

import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable {
    private String pseudo;
    private double scoreGame;
    private int difficulty;

    public Score(String pseudo, double timeSec, int difficulty){
        this.pseudo = pseudo;
        this.scoreGame = timeSec;
        this.difficulty = difficulty;
    }

    public double getTimeSec() {
        return scoreGame;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setTimeSec(double timeSec) {
        this.scoreGame = timeSec;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    public static Comparator<Score> ComparatorScoreGame = new Comparator<Score>() {

        @Override
        public int compare(Score score, Score t1) {
            return (int) (t1.getTimeSec() - score.getTimeSec());
        }

    };

}
