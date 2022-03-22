package com.iut.thegameship.UI.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Sprite;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.input.ECommand;
import com.iut.thegameship.util.loop.IObserver;

import java.util.Set;

public class GameView extends View implements IObserver {

    public World world;

    private double layoutWidth;
    private double layoutHeight;

    private IEntity player;
    public Set<IEntity> entities;

    public GameView(Context context, World world, double layoutWidth, double layoutHeight) {
        super(context);
        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
        this.world = world;
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom ) {
        // ..
    }

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
        System.out.println(event);
        if (X < layoutWidth/2) {
            world.getCurrentLevel().updatePlayer(ECommand.LEFT);
        }
        else {
            world.getCurrentLevel().updatePlayer(ECommand.RIGHT);
        }
        update();
        return true;
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        entities = world.getEntityCollection();
        player = world.getPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            entities.forEach((e) -> {
                int resID = getResources().getIdentifier(Sprite.cast(e).getSprite(), "drawable", getContext().getPackageName());
                Location l = Location.cast(e);
                Bitmap shipBitmap = BitmapFactory.decodeResource(getResources(), resID);
                c.drawBitmap(shipBitmap, null, new Rect((int) l.getX(),(int) l.getY(),(int) l.getX()+(int)l.getWidth(),(int) l.getY()+(int)l.getHeight()), null);
            });
        }
    }

    @Override
    public void update() {
        postInvalidate();
    }
}