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
        scores.add(new Score("Jean", 15555, 1));
        scores.add(new Score("Pierre",32855, 2));
        scores.add(new Score("Martin",12558, 1));
        scores.add(new Score("George",74555,3));
        scores.add(new Score("Julien",85548, 3));
        return scores;
    }
}
