package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

public class Map {

    private Ball ball;
    private Planet[] planetArray;
    private int numberOfPlanets;
    private boolean completed = false;
    private float offsetX, offsetY;
    private Camera camera;
    Paint paint;
    int blue = Color.BLUE;
    int red = Color.RED;
    Drawable background;

    public Map(int mapSizeX, int mapSizeY, int numberOfPlanets, Context context) {

        this.numberOfPlanets = numberOfPlanets;
        planetArray = new Planet[numberOfPlanets];
        ball = new Ball(context, mapSizeX, mapSizeY);
        camera = new Camera(mapSizeX, mapSizeY);
        paint = new Paint();
        background = ResourcesCompat.getDrawable(context.getResources(), R.drawable.background, null);

        // Made the planet location random
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i] = new Planet(75, (int)(Math.random()*mapSizeX), (int)(Math.random()*mapSizeY), red);
        }

    }

    public void render(Canvas canvas) {

        // Updating balls position
        ball.update();

        // Updating camera offset
        offsetX = camera.getCamX(ball.getX(), canvas.getWidth());
        offsetY = camera.getCamY(ball.getY(), canvas.getHeight());

        background.setBounds(0,0,canvas.getWidth(), canvas.getHeight());
        background.draw(canvas);

        // Drawing the planets
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i].render(canvas, offsetX, offsetY);
        }

        // Drawing the ball
        ball.render(canvas, offsetX, offsetY);

    }

    public boolean complete() {
        return completed;
    }

}
