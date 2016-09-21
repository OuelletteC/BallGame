package com.example.chris.ballgame;


import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;


public class GameActivity extends Activity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameManager(this));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}
