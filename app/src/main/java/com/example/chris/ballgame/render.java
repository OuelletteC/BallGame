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

    float x = 100, y = 100;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int width = 0, height = 0;

    public render(Context context) {
        super(context);

        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();

        width = canvas.getWidth();
        height = canvas.getHeight();

        int blue = Color.BLUE;
        paint.setColor(blue);

        canvas.drawCircle(x,y,50,paint);

        invalidate();
    }


    public void onSensorChanged(SensorEvent event) {

        x -= event.values[0] * 4;
        y += event.values[1] * 4;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
