package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Planet {

    private int size;
    private int x, y;

    public Planet(int x, int y, int size) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public void render(Canvas canvas, int offsetX, int offsetY, Drawable planetImg) {
        planetImg.setBounds(x- offsetX,y-offsetY,x-offsetX+size,y-offsetY+size);
        planetImg.draw(canvas);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getSize(){return this.size;}


}
