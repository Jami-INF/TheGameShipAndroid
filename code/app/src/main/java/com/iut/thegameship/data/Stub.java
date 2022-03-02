package com.iut.thegameship.data;

import android.util.Log;

import com.iut.thegameship.model.score.Score;

import java.util.ArrayList;

public class Stub {
    public ArrayList<Score> loadscoresTmp() {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("Jean", (float) 15.555));
        scores.add(new Score("Pierre",(float) 32.855));
        scores.add(new Score("Martin",(float) 12.558));
        scores.add(new Score("George",(float) 74.555));
        scores.add(new Score("Julien",(float) 85.548));
        return scores;

    }
}
