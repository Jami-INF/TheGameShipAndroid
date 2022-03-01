package com.iut.thegameship.data;

import android.util.Log;

import com.iut.thegameship.model.score.Score;

import java.util.ArrayList;

public class Stub {
    public ArrayList<Score> loadscoresTmp() {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("Jean",0));
        scores.add(new Score("Jean1,",0));
        scores.add(new Score("Jean3",0));
        scores.add(new Score("Jean4",0));
        scores.add(new Score("Jean5",0));
        return scores;

    }
}
