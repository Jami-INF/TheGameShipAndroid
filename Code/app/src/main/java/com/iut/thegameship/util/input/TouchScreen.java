package com.iut.thegameship.util.input;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class TouchScreen implements IInput {
    private ArrayList<ECommand> list = new ArrayList<>();
    private double initialPositionx;
    private double initialPositiony;
    private View layout;


    public TouchScreen(View layout) {
        this.layout = layout;
        setLocation();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setLocation(){
        layout.setOnTouchListener((view, motionEvent) -> {
            final int action = motionEvent.getAction();

            switch(action){
                case MotionEvent.ACTION_DOWN:
                    initialPositionx = motionEvent.getX();
                    initialPositiony = motionEvent.getY();
                case MotionEvent.ACTION_MOVE:

                    if(motionEvent.getX() < initialPositionx) {
                        //Gauche
                        list.add(ECommand.LEFT);
                    }
                    if(motionEvent.getX() > initialPositionx) {
                        //Droite
                        list.add(ECommand.RIGHT);
                    }
                    if(motionEvent.getY() > initialPositiony) {
                        //Bas
                        list.add(ECommand.DOWN);
                    }
                    if(motionEvent.getY() < initialPositiony) {
                        //Haut
                        list.add(ECommand.UP);
                    }
            }
            return true;
        });
    }

    @Override
    public ArrayList<ECommand> getKeyPressed() {
        //Faire une Queue à la place
        ArrayList<ECommand> list_bis = new ArrayList<>(list);
        list.clear();
        return list_bis;
    }
}

