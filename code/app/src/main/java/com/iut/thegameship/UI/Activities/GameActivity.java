package com.iut.thegameship.UI.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.util.loop.*;

import java.util.ArrayList;

public class GameActivity extends MainActivity implements IObserver {

    ArrayList<TextView> listTir = new ArrayList<>();
    ConstraintLayout layout;
    TextView text;
    Timer timer;
    Loop loop = new Loop(10);
    int text_width;
    int text_height;
    int layout_width;
    int layout_height;
    //MediaPlayer music;

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Thread thread = new Thread(loop);
        timer = new Timer(loop);

        text = findViewById(R.id.textMovable);
        layout = findViewById(R.id.gameView);
        //music = MediaPlayer.create(this, R.raw.shoot);

        loop.subscribe(this);
        loop.subscribe(timer);
        thread.start();

        //final TextView textNickNameTest = findViewById(R.id.nicknamebindtest);
        //textNickNameTest.setText("Vous etes : "+getIntent().getStringExtra("nickname"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        text.post(() -> {
            text_height = text.getMeasuredHeight();
            text_width = text.getMeasuredWidth();
            layout_height = layout.getMeasuredHeight();
            layout_width = layout.getMeasuredWidth();
            String string = layout_width + " " + layout_height;
            Log.d("Game", string);
            layout.setOnTouchListener((view, motionEvent) -> {
                text.setX(motionEvent.getX() - text_width/2);
                text.setY(motionEvent.getY() - text_height/2);
                return true;
            });
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        loop.StopLoop();
    }

    @Override
    public void update() {
        runOnUiThread(() -> {
            for (TextView text : listTir) {
                text.setY(text.getY() - 5);
            }
            if (timer.getTimer() > 1000) {
                Log.d("Tir", String.valueOf(listTir.size()));
                timer.resetTimer();
                TextView shoot = new TextView(this);
                shoot.setText("piou !");
                shoot.setY(text.getY() - text_height); // - text.getMeasuredHeight()
                shoot.setX(text.getX() + (text_width/2) - 45); // - (shoot.getMeasuredWidth()/2)
                listTir.add(shoot);
                layout.addView(shoot);
                //music.start();
            }
            layout.invalidate();
        });
    }
}
