package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
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
        map1 = new Map(15000, 3000, 150, context);
        currentMap = map1;
    }

    // Draw method that loops infinitely
    @Override
    public void onDraw(Canvas canvas) {

        // rendering the map, which will in turn render the ball and planets
        map1.render(canvas);

        // Re-calls this method
        invalidate();
    }
}
