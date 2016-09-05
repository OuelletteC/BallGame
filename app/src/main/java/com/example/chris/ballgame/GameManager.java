package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.graphics.drawable.Drawable;

public class GameManager extends View {

    int red = Color.RED;
    Drawable bgrnd = getResources().getDrawable(R.drawable.background);
    private Map map1;
    private Map map2;
    private Map currentMap;


    public GameManager(Context context) {
        super(context);
        //ball = new Ball(context);
        map1 = new Map(1000, 1600, 4, context);
        currentMap = map1;
        map2 = new Map(1000, 1600, 6, context);
    }

    // Draw method that loops infinitely
    @Override
    public void onDraw(Canvas canvas) {

        bgrnd.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        bgrnd.draw(canvas);

        // rendering the map, which will in turn render the ball and planets
        map1.render(canvas);

        invalidate();
    }
}
