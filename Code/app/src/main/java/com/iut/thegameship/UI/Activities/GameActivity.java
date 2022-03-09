package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.data.FileLoader;
import com.iut.thegameship.data.FileSaver;
import com.iut.thegameship.data.ILoad;
import com.iut.thegameship.data.ISave;
import com.iut.thegameship.data.Stub;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Sprite;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.model.score.Score;
import com.iut.thegameship.util.loop.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameActivity extends MainActivity implements IObserver {

    private ConstraintLayout layout;
    private int layoutWidth;
    private int layoutHeight;

    private Timer timer;
    private Loop loop;
    private Thread thread;

    private ImageView spaceShip;
    private int spaceShipWidth;
    private int spaceShipHeight;

    private MediaPlayer music;

    private World world;
    private IEntity player;

    public static final String PATHToScores = "scores";
    private ISave save = new FileSaver();
    private ILoad loader;
    private Stub modele = new Stub();
    private ArrayList<Score> scores = null;//a voir si ça reste ici


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        layout = findViewById(R.id.gameView);
        spaceShip = findViewById(R.id.spaceShip);
        music = MediaPlayer.create(this, R.raw.shoot);

        loader = new FileLoader();
        try {
            scores = (ArrayList<Score>) loader.load(openFileInput(PATHToScores));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        if (scores == null) {
            scores = (ArrayList<Score>) modele.load(null);
        }

        this.world = new World(layoutWidth, layoutHeight);
        world.init();
        loop = world.loop;

        timer = new Timer(loop);
        loop.subscribe(this);
        loop.subscribe(timer);

        layout.post(() ->{
            layoutHeight = layout.getMeasuredHeight();
            layoutWidth = layout.getMeasuredWidth();
        });
        spaceShip.setRotation(-90);

        Button buttonLeft = findViewById(R.id.goLeft);
        buttonLeft.setOnTouchListener((e, motionEvent) -> {
            System.out.println(motionEvent.getAction());
            spaceShip.setX(spaceShip.getX());   // A refaire pck c'est à chier
            return false;
        });

        Button buttonRight = findViewById(R.id.goRight);
        buttonRight.setOnTouchListener((e, motionEvent) -> {
            spaceShip.setX(spaceShip.getX() + 10);   // A refaire pck c'est à chier
            return false;
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        String nickname = getIntent().getStringExtra("nickname");
        if(nickname.equals("")){
            nickname = "guest";
        }
        scores.add(new Score(nickname, (float) 0));//Score test

        player = world.getPlayer();

        int resID = getResources().getIdentifier(Sprite.cast(player).getSprite() , "drawable" , getPackageName());
        spaceShip.setImageDrawable(getResources().getDrawable(resID));

        Location l = Location.cast(player);
        spaceShip.setX((float) l.getX());
        spaceShip.setY((float) l.getY());

        // Trouver une solution pour faire ça bien plus proprement !

        spaceShip.post(() -> {
            spaceShipHeight = spaceShip.getMeasuredHeight();   // Peut être à modifier pour s'adapter à la taille défini par le constructeur?
            spaceShipWidth = spaceShip.getMeasuredWidth();
        });
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
        try {
            save.save(openFileOutput(PATHToScores, MODE_PRIVATE), scores);
            System.out.println("save ok");
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                scores.forEach((n) -> System.out.println(n.getPseudo()));
            }*/

        } catch (FileNotFoundException e) {

        }
    }

    @Override
    public void update() {
        runOnUiThread(() -> {
            for (int index=0; index < ((ViewGroup) layout).getChildCount(); index++) {
                View childView = ((ViewGroup) layout).getChildAt(index);
                if (childView instanceof TextView) {
                    TextView child = (TextView) childView;
                    if (child.equals(spaceShip)) {
                        continue;
                    }
                    if (child.getText() == "|") {
                        child.setY(child.getY() - 5);
                    }
                    if (child.getY() < -(spaceShipHeight * 2)) {
                        layout.removeView(child);
                    }
                }
            }

            if (timer.getTimer() > 1000) {
                timer.resetTimer();

                TextView shoot = new TextView(this);
                shoot.setText("|");
                shoot.setX(spaceShip.getX() + (spaceShipWidth /2) - 4);     // Largeur du projectile
                shoot.setY(spaceShip.getY() - (spaceShipHeight /4));

                layout.addView(shoot);
                music.start();
            }
            layout.invalidate();
        });
    }
    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("nickname",nickname);
        return intent;
    }

}
