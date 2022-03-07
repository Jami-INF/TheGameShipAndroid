package com.iut.thegameship.util.input;

import java.util.ArrayList;

public class TouchScreen implements IInput{
    @Override
    public ArrayList<ECommand> getKeyPressed() {
        ArrayList<ECommand> list = new ArrayList<>();
        list.add(ECommand.UP);
        return list;
    }
}
