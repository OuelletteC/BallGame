package com.example.chris.ballgame;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import java.util.*;

public class Map {

    private Ball ball;
    ArrayList<Coin> coinList = new ArrayList<Coin>();
    private int numberOfCoins;
    private Heart[] heartArray;
    private int numberOfHearts;
    private Planet[] planetArray;
    private Camera camera;
    private int numberOfPlanets;
    private boolean completed = false;
    private float offsetX, offsetY;
    private Paint paint;
    private int blue = Color.BLUE;
    private Drawable background;
    private Drawable coinImg;
    private Drawable heartImg;
    private Drawable shipImg;
    private int mapSizeX, mapSizeY;

    public Map(int mapSizeX, int mapSizeY, int numberOfPlanets, int numberOfCoins,int numberOfHearts, Context context) {

        this.numberOfPlanets = numberOfPlanets;
        planetArray = new Planet[numberOfPlanets];
        this.numberOfCoins = numberOfCoins;
        this.numberOfHearts = numberOfHearts;
        heartArray = new Heart[numberOfHearts];

        // Made the planet location random
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i] = new Planet(75, (int)(Math.random()*mapSizeX), (int)(Math.random()*mapSizeY), blue);
        }
        // made coin locations random for now
        coinImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.coin, null);
        for (int i = 0; i < numberOfCoins; i++) {
            coinList.add(new Coin((int)(Math.random()*mapSizeX), (int)(Math.random()*mapSizeY),75, 1));
        }
        heartImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.heart, null);
        for (int i = 0; i < numberOfHearts; i++) {
            heartArray[i] = new Heart((int)(Math.random()*mapSizeX), (int)(Math.random()*mapSizeY),75);
        }

        ball = new Ball(context, mapSizeX, mapSizeY, planetArray);
        camera = new Camera(mapSizeX, mapSizeY);
        paint = new Paint();
        background = ResourcesCompat.getDrawable(context.getResources(), R.drawable.background, null);
        shipImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ship, null);
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;



    }

    public void render(Canvas canvas) {

        // Updating balls position
        ball.update();

        // Updating camera offset
        offsetX = camera.getCamX(ball.getX(), canvas.getWidth());
        offsetY = camera.getCamY(ball.getY(), canvas.getHeight());

        background.setBounds(0 - (int)offsetX, 0 - (int)offsetY, 1*mapSizeX - (int)offsetX, 1*mapSizeY - (int)offsetY);
        background.draw(canvas);



        // Drawing the planets
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i].render(canvas, offsetX, offsetY);
        }
        // Drawing the coins
        for (int i = 0; i < numberOfCoins; i++) {
            coinList.get(i).render(canvas, (int)offsetX, (int)offsetY, coinImg);
            if (Collision(ball.x,ball.y,ball.size,coinList.get(i).x,coinList.get(i).y,coinList.get(i).size)){
                coinList.remove(i);
                numberOfCoins--;
            }

        }
        for (int i = 0; i < numberOfHearts; i++) {

            heartArray[i].render(canvas, (int)offsetX, (int)offsetY, heartImg);
        }


        // Drawing the ball
        ball.render(canvas, (int)offsetX, (int)offsetY,shipImg);


    }

    public boolean complete() {
        return completed;
    }

    public boolean Collision(float planetX, float planetY, int planetSize, int shipX, int shipY, int shipSize) {

        if ((shipX + shipSize) >= planetX && shipX <= (planetX + planetSize) && (shipY + shipSize) >= planetY && shipY <= (planetY + planetSize)) {
            return true;
        }
        return false;

    }

}
