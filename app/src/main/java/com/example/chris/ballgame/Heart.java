package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Mark on 2016-09-13.
 */
public class Heart {

    int x;
    int y;
    int size;
    int value =1;

    public Heart(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void render(Canvas canvas, int offsetX, int offsetY,Drawable heartImg) {
        heartImg.setBounds(x- offsetX,y-offsetY,x-offsetX+size,y-offsetY+size);
        heartImg.draw(canvas);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getValue() {
        return this.value;
    }
    public int getSize(){return  this.size;}
}