package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Asteroid {

    private float x,y,vx,vy;
    private int size;


    public Asteroid(float x, float y,float vx,float vy, int size) {
        this.size = size;
        this.vx =vx;
        this.vy= vy;
        this.x = x;
        this.y = y;
    }

    public void render(Canvas canvas, float offsetX, float offsetY, Drawable asteroidImg) {
        asteroidImg.setBounds((int)(x-offsetX),(int)(y-offsetY),(int)(x-offsetX+size),(int)(y-offsetY+size));
        asteroidImg.draw(canvas);
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public int getSize(){return this.size;}
    public float getVx(){return this.vx;}
    public float getVy(){return this.vy;}

    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }



}

