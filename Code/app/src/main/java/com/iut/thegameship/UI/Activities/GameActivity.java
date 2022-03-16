package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.UI.Views.GameView;
import com.iut.thegameship.model.collider.Collider;
import com.iut.thegameship.model.collider.ICollider;
import com.iut.thegameship.model.entity.EntityManager;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.model.move.IMove;
import com.iut.thegameship.model.move.Move;
import com.iut.thegameship.util.input.TouchScreen;
import com.iut.thegameship.util.loop.*;

import java.util.Set;

public class GameActivity extends AppCompatActivity  {

    private ConstraintLayout layout;
    private LinearLayout linearLayout;
    private GameView gameview;
    private int layoutWidth;
    private int layoutHeight;

    private Timer timer;
    private Loop loop;
    private Thread thread;
    private World world;
    private IEntity player;

    private final EntityManager entityManager = new EntityManager();
    public Set<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

    private IMove move = new Move();
    private final ICollider collider = new Collider(getEntityCollection());

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
        linearLayout = findViewById(R.id.linearLayout);

        this.world = new World(layoutWidth, layoutHeight, new TouchScreen(layout));
        world.init();
        gameview = new GameView(this, world);

        loop = world.loop;

        timer = new Timer(loop);
        System.out.println();
        loop.subscribe(timer);
        linearLayout.addView(gameview);


        // Temporaire (debug)
        /*Button buttonTestEndGame = findViewById(R.id.buttonTestEndGame);
        buttonTestEndGame.setOnClickListener(e -> {
            Intent intent = EndGameActivity.newIntent(this, getIntent().getStringExtra("nickname"), scorePlayer);
            startActivity(intent);
        });*/

    }


    @Override
    protected void onStart() {
        super.onStart();
        String nickname = getIntent().getStringExtra("nickname");
        if (nickname.equals("")) {
            nickname = "guest";
        }
        //world.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //loop.StopLoop();
        //thread.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //thread = new Thread(loop);
        //thread.start();
        //loop.RestartLoop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //loop.StopLoop();
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("nickname",nickname);
        return intent;
    }


}
