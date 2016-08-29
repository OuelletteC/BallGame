package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GumDrop implements ItemADT {

    Paint paint = new Paint();
    int red = Color.RED;

    @Override
    public void render(Canvas canvas) {
        paint.setColor(red);
        canvas.drawRect(150,150,200,200,paint);
    }

}
