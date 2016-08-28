package com.example.chris.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

public class render extends View implements SensorEventListener {

    float x = 500, y = 500, v0x = 0, vx = 0, vy = 0, v0y = 0, time = (float).25, accx, accy, ball_size = 25, countx = 0, county=0, tempx, tempy;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int width = 0, height = 0;
    Paint paint = new Paint();
    int blue = Color.BLUE;

    public render(Context context) {
        super(context);

        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(blue);
        width = canvas.getWidth();
        height = canvas.getHeight();

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
        canvas.drawCircle(x, y, ball_size, paint);
        invalidate();
    }


   public void onSensorChanged(SensorEvent event) {

    /*
        vx = v0x + event.values[0] * time;
        vy = v0y + event.values[1] * time;
        x -= ((vx * vx - v0x * v0x) / (2 * event.values[0]));
        y += ((vy * vy - v0y * v0y) / (2 * event.values[1]));
        v0x = vx;
        v0y = vy;
*/
       accx = event.values[0];
       accy = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
