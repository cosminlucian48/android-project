package com.example.proiect_real.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.proiect_real.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);

                    startActivity(new Intent(SplashScreenActivity.this, LogInActivity.class));

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}