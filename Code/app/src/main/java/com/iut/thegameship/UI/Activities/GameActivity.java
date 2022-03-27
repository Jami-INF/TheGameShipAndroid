package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.UI.Views.GameView;
import com.iut.thegameship.model.entity.componement.Life;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.loop.*;

public class GameActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private LinearLayout linearLayout;
    private GameView gameview;
    private int layoutWidth;
    private int layoutHeight;

    private TextView textViewScore;
    private TextView textViewLife;

    private Loop loop;
    private World world;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        DisplayMetrics sizes = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizes);
        layoutWidth = sizes.widthPixels;
        layoutHeight = sizes.heightPixels;

        layout = findViewById(R.id.gameView);
        layout.post(() ->{
            layoutHeight = layout.getMeasuredHeight();
            layoutWidth = layout.getMeasuredWidth();
        });
        linearLayout = findViewById(R.id.linearLayout);

        this.world = new World(layoutWidth, layoutHeight);
        world.init();
        gameview = new GameView(this, world, layoutWidth, layoutHeight);

        loop = world.loop;
        loop.subscribe(gameview);
        linearLayout.addView(gameview);
        //bind score and life
        setTextViewScore(world.getScore());
        setTextViewLife(world.getLife());
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread(loop);
        thread.start();
        loop.RestartLoop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loop.StopLoop();
        world.exit();
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("nickname",nickname);
        return intent;
    }
    public void setTextViewScore (int score){
        textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText("Score : " + world.getScore());
    }
    public void setTextViewLife(int hp){
        textViewLife = findViewById(R.id.textViewLife);
        textViewLife.setText("Pv : " + hp);
    }

}
