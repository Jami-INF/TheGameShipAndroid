package com.iut.thegameship.data;

import com.iut.thegameship.model.score.Score;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Stub  implements ILoad {
    /*public ArrayList<Score> loadscoresTmp() {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("Jean", (float) 15.555));
        scores.add(new Score("Pierre",(float) 32.855));
        scores.add(new Score("Martin",(float) 12.558));
        scores.add(new Score("George",(float) 74.555));
        scores.add(new Score("Julien",(float) 85.548));
        return scores;
    }*/
    @Override
    public Serializable load(FileInputStream file) {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("Jean", (float) 15.555));
        scores.add(new Score("Pierre",(float) 32.855));
        scores.add(new Score("Martin",(float) 12.558));
        scores.add(new Score("George",(float) 74.555));
        scores.add(new Score("Julien",(float) 85.548));
        return scores;
    }
}
