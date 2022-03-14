package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.input.TouchScreen;
import com.iut.thegameship.util.loop.*;

public class GameActivity extends MainActivity implements IObserver {

    private ConstraintLayout layout;
    private int layoutWidth;
    private int layoutHeight;

    private Timer timer;
    private Loop loop;
    private Thread thread;
    private World world;

    private double scorePlayer = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        layout = findViewById(R.id.gameView);

        layout.post(() ->{
            layoutHeight = layout.getMeasuredHeight();
            layoutWidth = layout.getMeasuredWidth();
        });

        this.world = new World(layoutWidth, layoutHeight, new TouchScreen(layout));
        world.init();
        loop = world.loop;

        timer = new Timer(loop);
        loop.subscribe(this);
        loop.subscribe(timer);

        // Temporaire (debug)
        Button buttonTestEndGame = findViewById(R.id.buttonTestEndGame);
        buttonTestEndGame.setOnClickListener(e -> {
            Intent intent = EndGameActivity.newIntent(this, getIntent().getStringExtra("nickname"), scorePlayer);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String nickname = getIntent().getStringExtra("nickname");
        if (nickname.equals("")) {
            nickname = "guest";
        }
        world.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loop.StopLoop();
        thread.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thread = new Thread(loop);
        thread.start();
        loop.RestartLoop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loop.StopLoop();
    }

    @Override
    public void update() {
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("nickname",nickname);
        return intent;
    }
}
