package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.graphics.drawable.Drawable;

public class GameManager extends SurfaceView {

    //private SurfaceHolder holder;
    private Thread thread;
    private int red = Color.RED;
    private Map map1;
    private Map map2;
    private Map currentMap;

    public GameManager(Context context) {
        super(context);
        //holder = getHolder();
        //holder.addCallback(this);

        map1 = new Map(15000, 3000, 150, context);
        currentMap = map1;

        setWillNotDraw(false);
    }

    /*
    // Called when the surface is created
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }*/

    // Draw method that loops infinitely
    @Override
    public void onDraw(Canvas canvas) {

        // rendering the map, which will in turn render the ball and planets
        map1.render(canvas);

        // Re-calls this method
        invalidate();
    }
}
