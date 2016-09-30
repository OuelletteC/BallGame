package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Asteroid {

    private int size,x,y,vx,vy;


    public Asteroid(int x, int y,int vx,int vy, int size) {
        this.size = size;
        this.vx =vx;
        this.vy=vy;
        this.x = x;
        this.y = y;
    }

    public void render(Canvas canvas, int offsetX, int offsetY, Drawable asteroidImg) {
        asteroidImg.setBounds(x- offsetX,y-offsetY,x-offsetX+size,y-offsetY+size);
        asteroidImg.draw(canvas);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getSize(){return this.size;}


}

