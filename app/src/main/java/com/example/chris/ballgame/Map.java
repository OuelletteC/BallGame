package com.example.chris.ballgame;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import java.util.*;
import android.graphics.Paint;

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

    //in game text
    private int numCoinsCollected = 0;
    private Paint p = new Paint();



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

            asteroidList.add(new Asteroid((int)(Math.random()*(mapSizeX-60)), (int)(Math.random()*(mapSizeY-60)),
            (float)Math.random()*10,(float)Math.random()*10,60));
        }

        // made coin locations random for now
        coinImg = ResourcesCompat.getDrawable(context.getResources(), R.drawable.coin, null);
        for (int i = 0; i < numberOfCoins; i++) {
            coinList.add(new Coin((int)(Math.random()*(mapSizeX-60)), (int)(Math.random()*(mapSizeY-60)),60, 1));
            // prevents coins from being in the same location as planets
            inner:
            for (int j=0;j<numberOfPlanets;j++){

                if (CircleCollision(planetArray[j].getX()+planetArray[j].getSize()/2,
                planetArray[j].getY()+planetArray[j].getSize()/2, planetArray[j].getSize()/2,
                coinList.get(i).getX()+coinList.get(i).getSize()/2,
                coinList.get(i).getY()+coinList.get(i).getSize()/2,
                coinList.get(i).getSize()/2)) {

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
            for (int j = 0;j<numberOfPlanets;j++){

                if (CircleCollision(planetArray[j].getX()+planetArray[j].getSize()/2,
                planetArray[j].getY()+planetArray[j].getSize()/2,
                planetArray[j].getSize()/2,heartList.get(i).getX()+heartList.get(i).getSize()/2,
                heartList.get(i).getY()+heartList.get(i).getSize()/2,
                heartList.get(i).getSize()/2)) {

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

        p.setTextSize(25);
        p.setColor(context.getResources().getColor(android.R.color.holo_green_light));


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

            if (onScreen(offsetX,offsetY,canvas.getWidth(),canvas.getHeight(),

                    planetArray[i].getX(),planetArray[i].getY(),planetArray[i].getSize(),planetArray[i].getSize())) {
                    planetArray[i].render(canvas, (int) offsetX, (int) offsetY, planetImg);

                    if (CircleCollision(planetArray[i].getX()+planetArray[i].getSize()/2,
                    planetArray[i].getY()+planetArray[i].getSize()/2,
                    planetArray[i].getSize()/2, ball.getX()+ball.getSize()/2 ,
                    ball.getY()+ball.getSize()/2,ball.getSize()/2)) {

                        ball.setV0x(-ball.getV0x());
                        ball.setV0y(-ball.getV0y());
                    }
            }
        }

        // Drawing the coins
        for (int i = 0; i < numberOfCoins; i++) {
            // checks if object is on screen before rendering and colision detecting
            if (onScreen(offsetX,offsetY,canvas.getWidth(),canvas.getHeight(),
                    coinList.get(i).getX(),coinList.get(i).getY(),coinList.get(i).getSize(),coinList.get(i).getSize())) {

                coinList.get(i).render(canvas, (int) offsetX, (int) offsetY, coinImg);

                if (CircleCollision(ball.getX()+ball.getSize()/2 ,
                ball.getY()+ball.getSize()/2,ball.getSize()/2,
                coinList.get(i).getX()+coinList.get(i).getSize()/2,
                coinList.get(i).getY()+coinList.get(i).getSize()/2,
                coinList.get(i).getSize()/2)) {

                    coinList.remove(i);
                    numberOfCoins--;
                    numCoinsCollected += 1;
                }
            }
        }

        for (int i = 0; i < numberOfHearts; i++) {
            if (onScreen(offsetX,offsetY,canvas.getWidth(),canvas.getHeight(),
                    heartList.get(i).getX(),heartList.get(i).getY(),heartList.get(i).getSize(),heartList.get(i).getSize())) {

                heartList.get(i).render(canvas, (int) offsetX, (int) offsetY, heartImg);
                if (CircleCollision(ball.getX()+ball.getSize()/2 ,
                        ball.getY()+ball.getSize()/2,ball.getSize()/2,
                        heartList.get(i).getX()+heartList.get(i).getSize()/2,
                        heartList.get(i).getY()+heartList.get(i).getSize()/2,
                        heartList.get(i).getSize()/2)){

                    heartList.remove(i);
                    numberOfHearts--;
                }

            }
        }

        //rendering randomly rn
        for (int i = 0; i < numberOfAsteroids; i++) {
            asteroidList.get(i).render(canvas, (int)offsetX, (int)offsetY,asteroidImg);
            asteroidList.get(i).update();
        }

        // Drawing the ball
        ball.render(canvas, (int)offsetX, (int)offsetY,shipImg);

        //display stats
        canvas.drawText("Coins Collected: "+numCoinsCollected,ball.getX()-offsetX-canvas.getWidth()/2+ball.getSize()/2
                ,ball.getY()-offsetY-canvas.getHeight()/2+ball.getSize(),p);

    }

    public boolean complete() {
        return completed;
    }

    public boolean CircleCollision(float x1, float y1, float r1, float x2,float y2, float r2) {

        float xDistance = x2 - x1;
        float yDistance = y2 - y1;

        double hypotenuse = Math.sqrt(xDistance*xDistance + yDistance*yDistance);

        if (hypotenuse > (r1+r2)) {
            return false;
        }
        return true;

    }

    public float CircleCollisionUpdate(){return (float)1;}

    //checks if an objects location is on the on the screen
    public boolean onScreen(float screenX, float screenY, int screenWidth, int screenHeight,
                            int objX, int objY, int objWidth, int objHeight) {

        if ((objX+objWidth) >= screenX && objX <= (screenX + screenWidth) && (objY+objHeight) >= screenY && objY <= (screenY + screenHeight)) {
            return true;
        }
        return false;

    }


}
