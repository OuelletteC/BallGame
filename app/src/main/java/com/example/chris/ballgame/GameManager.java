package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.graphics.drawable.Drawable;

public class GameManager extends View {

    int red = Color.RED;
    private GumDrop gumDrop;
    private Ball ball;
    Drawable bgrnd = getResources().getDrawable(R.drawable.background);

    public GameManager(Context context) {
        super(context);
        gumDrop = new GumDrop();
        ball = new Ball(context);

    }

    // Draw method that loops infinitely
    @Override
    public void onDraw(Canvas canvas) {

        bgrnd.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        bgrnd.draw(canvas);
        ball.render(canvas);
        gumDrop.render(canvas);

        invalidate();
    }
}
