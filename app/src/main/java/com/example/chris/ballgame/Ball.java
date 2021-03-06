package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.graphics.drawable.Drawable;

public class Ball implements SensorEventListener {

    private float x = 750, y = 1500, v0x = 0, vx = 0, vy = 0, v0y = 0, time = (float).25, accx, accy, radius = 35;
    private int width, height, size = 100;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    Planet[] planetArray;

    public Ball(Context context, int mapSizeX, int mapSizeY, Planet[] planetArray) {
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
        this.width = mapSizeX;
        this.height = mapSizeY;
        this.planetArray = planetArray;
    }

    public void update() {

        if (x < 0){
            x = 0;
            vy = v0y + accy * time;
            y += ((vy * vy - v0y * v0y) / (2 * accy));
            v0y = vy;
            v0x = (float)-0.5*v0x;
        }
        else if (x > (width -size)){
            x = width-size;
            vy = v0y + accy * time;
            y += ((vy * vy - v0y * v0y) / (2 * accy));
            v0y = vy;
            v0x = (float)-0.5*v0x;
        }
        else if (y > height -size){
            y = height -size;
            vx = v0x + accx * time;
            x -= ((vx * vx - v0x * v0x) / (2 * accx));
            v0x = vx;
            v0y = (float)-0.5*v0y;
        }
        else if (y < 0){
            y = 0;
            vx = v0x + accx * time;
            x -= ((vx * vx - v0x * v0x) / (2 * accx));
            v0x = vx;
            v0y = (float)-0.5*v0y;
        }
        else {
            vx = v0x + accx * time;
            vy = v0y + accy * time;
            x -= ((vx * vx - v0x * v0x) / (2 * accx));
            y += ((vy * vy - v0y * v0y) / (2 * accy));
            v0x = vx;
            v0y = vy;
        }

    }

    // Method to draw the ball
    public void render(Canvas canvas, int offsetX, int offsetY, Drawable shipImg) {
        shipImg.setBounds((int)x-offsetX,(int)y-offsetY, (int)x-offsetX+size,(int)y-offsetY+size);
        shipImg.draw(canvas);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accx = sensorEvent.values[0];
        accy = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public boolean planetCollision(float planetX, float planetY, int planetRadius) {

        float xDistance = x - planetX;
        float yDistance = y - planetY;

        double hypotenuse = Math.sqrt(xDistance*xDistance + yDistance*yDistance);

        if (hypotenuse > planetRadius + this.radius) {
            return false;
        }
        return true;

    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getSize() {return size;}
    public float getVx() {return vx;}
    public float getVy() {return vy;}
    public float getV0x() {return v0x;}
    public float getV0y() {return v0y;}

    public void setVx(float vx){this.vx = vx;};
    public void setVy(float vy){this.vy = vy;};
    public void setV0x(float v0x){this.v0x = v0x;};
    public void setV0y(float v0y){this.v0y = v0y;};
}
