package com.iut.thegameship.UI.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

    ConstraintLayout layout;
    int layout_width;
    int layout_height;

    Timer timer;
    Loop loop;
    Thread thread;

    ImageView spaceShip;
    int spaceShip_width;
    int spaceShip_height;

    MediaPlayer music;

    World world;
    IEntity player;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        layout = findViewById(R.id.gameView);
        music = MediaPlayer.create(this, R.raw.shoot);

        this.world = new World(layout_width, layout_height);
        world.init();
        loop = world.loop;

        timer = new Timer(loop);
        loop.subscribe(this);
        loop.subscribe(timer);

        layout.post(() ->{
            layout_height = layout.getMeasuredHeight();
            layout_width = layout.getMeasuredWidth();
        });
        spaceShip.setRotation(-90);
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

        spaceShip.post(() -> {
            spaceShip_height = spaceShip.getMeasuredHeight();
            spaceShip_width = spaceShip.getMeasuredWidth();
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
                if (childView instanceof  TextView) {
                    TextView child = (TextView) childView;
                    if (child.equals(spaceShip)) {
                        continue;
                    }
                    child.setY(child.getY() - 5);
                    if (child.getY() < -(spaceShip_height * 2)) {
                        layout.removeView(child);
                    }
                }
            }
            if (timer.getTimer() > 1000) {
                timer.resetTimer();

                TextView shoot = new TextView(this);
                shoot.setText("|");
                shoot.setX(spaceShip.getX() - (spaceShip_width/2)- 45);
                shoot.setY(spaceShip.getY() - (spaceShip_height/4));

                layout.addView(shoot);
                music.start();
            }
            layout.invalidate();
        });
    }
}
