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

    private ArrayList<Coin> coinList = new ArrayList<Coin>();
    private int numberOfCoins;

    private ArrayList<Heart> heartList = new ArrayList<Heart>();
    private int numberOfHearts;

    private Planet[] planetArray;
    private int numberOfPlanets;

    private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
    private int numberOfAsteroids;

    private Camera camera;
    private int mapSizeX, mapSizeY;
    private boolean completed = false;
    private float offsetX, offsetY;

    private Drawable planetImg;
    private Drawable background;
    private Drawable coinImg;
    private Drawable heartImg;
    private Drawable shipImg;
    private Drawable coinCollected;
    private Drawable asteroidImg;


    public Map(int mapSizeX, int mapSizeY, int numberOfPlanets, int numberOfCoins,int numberOfHearts,int numberOfAsteroids, Context context) {

        this.numberOfPlanets = numberOfPlanets;
        planetArray = new Planet[numberOfPlanets];
        this.numberOfCoins = numberOfCoins;
        this.numberOfHearts = numberOfHearts;
        this.numberOfAsteroids  = numberOfAsteroids;

        // Made the planet location random
        planetImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.planet1, null);
        for (int i = 0; i < numberOfPlanets; i++) {
            planetArray[i] = new Planet((int)(Math.random()*(mapSizeX-200)), (int)(Math.random()*(mapSizeY-200)),200);
        }

        asteroidImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.asteroid2, null);
        for (int i = 0; i < numberOfAsteroids; i++) {
            asteroidList.add(new Asteroid((int)(Math.random()*(mapSizeX-60)), (int)(Math.random()*(mapSizeY-60)),10,10,60));
        }
        // made coin locations random for now
        coinImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.coin, null);
        for (int i = 0; i < numberOfCoins; i++) {
            coinList.add(new Coin((int)(Math.random()*(mapSizeX-60)), (int)(Math.random()*(mapSizeY-60)),60, 1));
            // prevents coins from being in the same location as planets
            inner:
            for (int j=0;j<numberOfPlanets;j++){
            if (Collision(coinList.get(i).getX(),coinList.get(i).getY(),coinList.get(i).getSize(),planetArray[j].getX(),planetArray[j].getY(),planetArray[j].getSize())){
                coinList.remove(i);
                i--;
                break inner;}
            }
        }

        heartImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.heart, null);
        for (int i = 0; i < numberOfHearts; i++) {
            heartList.add(new Heart((int)(Math.random()*(mapSizeX-65)), (int)(Math.random()*(mapSizeY-65)),65));
            //prevents hearts from being in the same location as planetsr
            inner2:
            for (int j=0;j<numberOfPlanets;j++){
                if (Collision(heartList.get(i).getX(),heartList.get(i).getY(),heartList.get(i).getSize(),planetArray[j].getX(),planetArray[j].getY(),planetArray[j].getSize())){
                    heartList.remove(i);
                    i--;
                    break inner2;}
            }
        }

        ball = new Ball(context, mapSizeX, mapSizeY, planetArray);
        camera = new Camera(mapSizeX, mapSizeY);
        background = ResourcesCompat.getDrawable(context.getResources(), R.drawable.background, null);
        shipImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ship, null);
        coinCollected = ResourcesCompat.getDrawable(context.getResources(), R.drawable.coincollected, null);
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;



    }

    public void render(Canvas canvas) {

        // Updating balls position
        ball.update();

        // Updating camera offset
        offsetX = camera.getCamX(ball.getX()+ball.getSize()/2, canvas.getWidth());
        offsetY = camera.getCamY(ball.getY()+ball.getSize()/2, canvas.getHeight());

        background.setBounds(0 - (int)offsetX, 0 - (int)offsetY, 1*mapSizeX - (int)offsetX, 1*mapSizeY - (int)offsetY);
        background.draw(canvas);


        // Drawing the planets
        for (int i = 0; i < numberOfPlanets; i++) {

            planetArray[i].render(canvas, (int)offsetX, (int)offsetY, planetImg);
            if (Collision(ball.getX(),ball.getY(),ball.getSize(),planetArray[i].getX(),planetArray[i].getY(),planetArray[i].getSize())){
            }
        }
        // Drawing the coins
        for (int i = 0; i < numberOfCoins; i++) {
            coinList.get(i).render(canvas, (int)offsetX, (int)offsetY, coinImg);
            if (Collision(ball.getX(),ball.getY(),ball.getSize(),coinList.get(i).getX(),coinList.get(i).getY(),coinList.get(i).getSize())){
                coinList.remove(i);
                numberOfCoins--;
            }

        }
        for (int i = 0; i < numberOfHearts; i++) {
            heartList.get(i).render(canvas, (int)offsetX, (int)offsetY, heartImg);
            if (Collision(ball.getX(),ball.getY(),ball.getSize(),heartList.get(i).x,heartList.get(i).y,heartList.get(i).size)){
                heartList.remove(i);
                numberOfHearts--;
            }
        }
        for (int i = 0; i < numberOfAsteroids; i++) {
            asteroidList.get(i).render(canvas, (int)offsetX, (int)offsetY,asteroidImg);
            asteroidList.get(i).setX(asteroidList.get(i).getX()+asteroidList.get(i).getVx());
        }

        // Drawing the ball
        ball.render(canvas, (int)offsetX, (int)offsetY,shipImg);

    }

    public boolean complete() {
        return completed;
    }

    public boolean Collision(float planetX, float planetY, int planetSize, int shipX, int shipY, int shipSize) {

        if ((shipX + shipSize-20) >= planetX && shipX <= (planetX + planetSize-20) && (shipY + shipSize-20) >= planetY && shipY <= (planetY + planetSize-20)) {
            return true;
        }
        return false;

    }
    public boolean onScreen(float planetX, float planetY, int planetSize, int shipX, int shipY, int shipSize) {

        if ((shipX + shipSize-20) >= planetX && shipX <= (planetX + planetSize-20) && (shipY + shipSize-20) >= planetY && shipY <= (planetY + planetSize-20)) {
            return true;
        }
        return false;

    }

}
