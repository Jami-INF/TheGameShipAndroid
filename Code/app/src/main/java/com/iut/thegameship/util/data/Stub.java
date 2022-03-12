package com.iut.thegameship.util.data;

import com.iut.thegameship.model.score.Score;
import com.iut.thegameship.util.save.ILoad;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Stub  implements ILoad {
    @Override
    public Serializable load(FileInputStream file) {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("Jean", 15.555, 1));
        scores.add(new Score("Pierre",32.855, 2));
        scores.add(new Score("Martin",12.558, 1));
        scores.add(new Score("George",74.555,3));
        scores.add(new Score("Julien",85.548, 3));
        return scores;
    }
}
