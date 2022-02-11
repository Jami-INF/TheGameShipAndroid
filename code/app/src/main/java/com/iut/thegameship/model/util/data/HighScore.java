package com.iut.thegameship.model.util.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HighScore {

    private final List<String> highScore;

    public HighScore() { highScore = new ArrayList<>(); }
    public HighScore(ArrayList<String> list) { highScore = list; }

    public void addHighScore(int score) {
        highScore.add(String.valueOf(score) + " : " + new Date());
        if (highScore.contains("No Score Yet")){
            highScore.remove("No Score Yet");
        }
    }
    public List<String> getListScore() {
        /*
        if(highScore.isEmpty()){
            highScore.add("No Score Yet");
        }*/
        return highScore;
    }
    public void resetHighScore() { highScore.removeAll(highScore); }
    public void loadListe(ArrayList l) {
        resetHighScore();
        highScore.addAll(l);
    }

    @Override
    public String toString() {
        String str = "Scores :\n";
        for (String s : highScore) {
            str = str + s + "\n";
        }
        return str;
    }
}
