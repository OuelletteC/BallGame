package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Ball implements SensorEventListener {

    float x = 750, y = 1500, v0x = 0, vx = 0, vy = 0, v0y = 0, time = (float).25, accx, accy, ball_size = 35;
    private int width, height;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    Paint paint = new Paint();
    int green = Color.GREEN;

    public Ball(Context context, int mapSizeX, int mapSizeY) {
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
        this.width = mapSizeX;
        this.height = mapSizeY;
    }

    public void update() {

        if (x < ball_size/2){
            x = ball_size/2;
            vy = v0y + accy * time;
            y += ((vy * vy - v0y * v0y) / (2 * accy));
            v0y = vy;
            v0x = 0;
        }
        else if (x > width-ball_size/2){
            x = width-ball_size/2;
            vy = v0y + accy * time;
            y += ((vy * vy - v0y * v0y) / (2 * accy));
            v0y = vy;
            v0x = 0;
        }
        else if (y > height-ball_size/2){
            y = height-ball_size/2;
            vx = v0x + accx * time;
            x -= ((vx * vx - v0x * v0x) / (2 * accx));
            v0x = vx;
            v0y = 0;
        }
        else if (y < ball_size/2){
            y = ball_size/2;
            vx = v0x + accx * time;
            x -= ((vx * vx - v0x * v0x) / (2 * accx));
            v0x = vx;
            v0y = 0;
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
    public void render(Canvas canvas, float offsetX, float offsetY) {
        paint.setColor(green);
        canvas.drawCircle(x - offsetX, y - offsetY, 30, paint);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accx = sensorEvent.values[0];
        accy = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
