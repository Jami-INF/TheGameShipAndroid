package com.iut.thegameship.UI.Views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.iut.thegameship.R;
import com.iut.thegameship.UI.Activities.EndGameActivity;
import com.iut.thegameship.model.entity.EEntityType;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Shoot;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.input.ECommand;
import com.iut.thegameship.util.loop.IObserver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameView extends View implements IObserver {
    public World world;

    private final double layoutWidth;
    private final double layoutHeight;

    private Bitmap bitmapShip;
    private Bitmap bitmapShoot;
    private Bitmap bitmapShoot2;
    private Bitmap bitmapEnemy;

    String nickname;

    private IEntity player;
    public Set<IEntity> entities = new HashSet<IEntity>();

    public GameView(Context context, World world, double layoutWidth, double layoutHeight, String nickname) {
        super(context);
        init();
        this.nickname = nickname;
        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
        this.world = world;

    }
    public void init(){
        bitmapShip = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        bitmapShoot = BitmapFactory.decodeResource(getResources(), R.drawable.missile);
        bitmapShoot2 = BitmapFactory.decodeResource(getResources(), R.drawable.missile2);
        bitmapEnemy = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
    }


    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom ) {}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        if (X < layoutWidth/2) {
            world.getCurrentLevel().updatePlayer(ECommand.LEFT);
        }
        else {
            world.getCurrentLevel().updatePlayer(ECommand.RIGHT);
        }
        return true;
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        Set<IEntity> entitiestmp = world.getEntityCollection();
        entities.clear();
        entities.addAll(entitiestmp);

        player = world.getPlayer();
        Iterator it = entities.iterator();
        while (it.hasNext()) {
            IEntity e = (IEntity)it.next();
            Location l = Location.cast(e);
            //delete shoot witch are out of the screen
            if (e.getEntityType() == EEntityType.Shoot) {
                if (l.getX() > layoutWidth || l.getX() < 0 || l.getY() > layoutHeight || l.getY() < 0) {
                    it.remove();
                    //Log.d("Shoot", "remove");
                }
            }
            switch (e.getEntityType()) {
                case Shoot:
                    if (Shoot.cast(e).getOwnerId() == player.getId()) {c.drawBitmap(this.bitmapShoot, null, new Rect((int) l.getX(), (int) l.getY(), (int) l.getX() + (int) l.getWidth(), (int) l.getY() + (int) l.getHeight()), null); }
                    else { c.drawBitmap(this.bitmapShoot2, null, new Rect((int) l.getX(), (int) l.getY(), (int) l.getX() + (int) l.getWidth(), (int) l.getY() + (int) l.getHeight()), null); }
                    break;
                case Player:
                    //lancer l'activité EndGame si le joueur est mort
                    if (world.getLife() <= 0) {
                        Intent intent = EndGameActivity.newIntent(getContext(), nickname, world.getScore());
                        getContext().startActivity(intent);
                    }
                    c.drawBitmap(this.bitmapShip, null, new Rect((int) l.getX(), (int) l.getY(), (int) l.getX() + (int) l.getWidth(), (int) l.getY() + (int) l.getHeight()), null);
                    break;
                case Enemy:
                    c.drawBitmap(this.bitmapEnemy, null, new Rect((int) l.getX(), (int) l.getY(), (int) l.getX() + (int) l.getWidth(), (int) l.getY() + (int) l.getHeight()), null);
                    break;
            }
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        c.drawText("Score : " + world.getScore(), 30, 80, paint);
        c.drawText("Life : " + world.getLife(), 500, 80, paint);
        /*textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText("Score : " + 22);
        textViewLife = findViewById(R.id.textViewLife);
        textViewLife.setText("Pv : " + 12);*/
    }

    @Override
    public void update() {
        postInvalidate();
    }
}


