package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Sprite;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.loop.*;

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


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        layout = findViewById(R.id.gameView);
        spaceShip = findViewById(R.id.spaceShip);
        music = MediaPlayer.create(this, R.raw.shoot);

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
}
