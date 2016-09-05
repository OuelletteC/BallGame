package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Planet {

    private int radius;
    private int x, y;
    private int color;
    Paint paint;

    public Planet(int radius, int x, int y, int color) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.color = color;
        paint = new Paint();
    }

    public void render(Canvas canvas) {
        paint.setColor(this.color);
        canvas.drawCircle(x, y, radius, paint);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

}
