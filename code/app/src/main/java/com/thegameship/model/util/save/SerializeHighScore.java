package com.thegameship.model.util.save;

import com.thegameship.model.util.data.HighScore;
import java.util.ArrayList;

public class SerializeHighScore {

    private ArrayList<String> listHighScore =  new ArrayList<>();

    public SerializeHighScore() {
        listHighScore.add("No Score Yet");
    }
    public SerializeHighScore(HighScore highScore) {
        listHighScore.addAll(highScore.getListScore());
    }

    public ArrayList<String> getListHighScore() { return listHighScore; }
    public void setListHighScore(ArrayList<String> l) { this.listHighScore = l; }

    @Override
    public String toString() {
        return "SerializeSettings{" +
                "listHighScore=" + listHighScore +
                '}';
    }
}
