package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class GameManager extends View {

    int red = Color.RED;
    private GumDrop gumDrop;
    private Ball ball;

    public GameManager(Context context) {
        super(context);
        gumDrop = new GumDrop();
        ball = new Ball(context);
    }

    // Draw method that loops infinitely
    @Override
    public void onDraw(Canvas canvas) {

        gumDrop.render(canvas);
        ball.render(canvas);

        invalidate();
    }
}
