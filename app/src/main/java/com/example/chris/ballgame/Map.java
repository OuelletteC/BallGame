package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class Map {

    private Ball ball;
    private Planet[] planetArray;
    private int numberOfPlanets;
    private boolean complete = false;

    public Map(int mapSizeX, int mapSizeY, int numberOfPlanets, Context context) {

        this.numberOfPlanets = numberOfPlanets;
        planetArray = new Planet[numberOfPlanets];
        ball = new Ball(context);

        int grey = Color.GRAY;

        // Made the planet location random for now
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i] = new Planet(75, (int)(Math.random()*mapSizeX), (int)(Math.random()*mapSizeY), grey);
        }

    }

    public void render(Canvas canvas) {

        ball.render(canvas);

        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i].render(canvas);
        }

    }

    public boolean complete() {
        return complete;
    }

}
