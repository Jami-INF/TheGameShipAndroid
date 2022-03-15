package com.iut.thegameship.UI.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iut.thegameship.R;
import com.iut.thegameship.model.game.World;
import com.iut.thegameship.util.input.TouchScreen;
import com.iut.thegameship.util.loop.IObserver;
import com.iut.thegameship.util.loop.Loop;
import com.iut.thegameship.util.loop.Timer;

public class GameView extends View implements IObserver {

    private ConstraintLayout layout;
    private int layoutWidth;
    private int layoutHeight;

    public World world;
    public Timer timer;
    public Loop loop;
    public Thread thread;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public GameView(Context context) {
        super(context);
        layout = findViewById(R.id.gameView);

        layout.post(() ->{
            layoutHeight = layout.getMeasuredHeight();
            layoutWidth = layout.getMeasuredWidth();
        });
        world = new World(layoutWidth, layoutHeight, new TouchScreen(layout));
        world.init();
        loop = world.loop;

        timer = new Timer(loop);
        loop.subscribe(this);
        loop.subscribe(timer);
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

    protected void onDraw(Canvas c) {
        super.onDraw(c);
    }

    @Override
    public void update() {

    }
}
