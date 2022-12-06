package com.akshaj02.shopnsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //new handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //start the main activity
                startActivity(new Intent(SplashScreen.this, Onboard.class));
                //finish this activity
                finish();
            }
        }, 4000);
    }
}