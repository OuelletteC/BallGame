package com.example.chris.ballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Mark on 2016-09-13.
 */
public class Coin {

    int x;
    int y;
    int size;
    int value;
    //private Drawable coinImg;

    public Coin(int x, int y, int size, int value){
        this.x = x;
        this.y = y;
        this.size = size;
        this.value = value;
        //this.coinImg = coinImg;
    }

    public void render(Canvas canvas, int offsetX, int offsetY,Drawable coinImg) {
        coinImg.setBounds(x- offsetX,y-offsetY,x-offsetX+size,y-offsetY+size);
        coinImg.draw(canvas);
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
}
