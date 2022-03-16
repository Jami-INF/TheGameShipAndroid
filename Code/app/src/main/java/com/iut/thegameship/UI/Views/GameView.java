package com.iut.thegameship.UI.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.model.entity.IEntity;
import com.iut.thegameship.model.entity.componement.Location;
import com.iut.thegameship.model.entity.componement.Sprite;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.input.TouchScreen;
import com.iut.thegameship.util.loop.IObserver;
import com.iut.thegameship.util.loop.Loop;
import com.iut.thegameship.util.loop.Timer;

import java.util.Set;

public class GameView extends View implements IObserver {

    private ConstraintLayout layout;
    private int layoutWidth;
    private int layoutHeight;

    public World world;


    private ImageView spaceShip;
    private int spaceShipWidth;
    private int spaceShipHeight;

    private IEntity player;
    public Set<IEntity> entities;


    public GameView(Context context, World world){
        super(context);
        this.world = world;
    }


    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom ) {
        //placer les objets de la gameview
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
    public boolean onTouchEvent(MotionEvent event){
        float X = event.getX();
        Location l = Location.cast(player);
        l.setX(X);
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
                //System.out.println(l.getX()+" "+ l.getY());
                c.drawBitmap(shipBitmap, null, new Rect((int) l.getX(),(int) l.getY(),(int) l.getX()+(int)l.getWidth(),(int) l.getY()+(int)l.getHeight()), null);
            });
        }


        /*int resID = getResources().getIdentifier(Sprite.cast(player).getSprite(), "drawable", getContext().getPackageName());
        Location l = Location.cast(player);
        Bitmap shipBitmap = BitmapFactory.decodeResource(getResources(), resID);
        c.drawBitmap(shipBitmap, null, new Rect((int) l.getX(),(int) l.getY(),(int) l.getX()+(int)l.getWidth(),(int) l.getY()+(int)l.getHeight()), null);
        */

    }

    @Override
    public void update() {
        postInvalidate();
    }
}
