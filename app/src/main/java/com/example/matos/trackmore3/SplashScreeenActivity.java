package com.example.matos.trackmore3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreeenActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screeen);

        // Make the change from MenuActivity to HomeActivity after 3 sec (SPLASH_TIMEOUT)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent = new Intent(SplashScreeenActivity.this, HomeActivity.class);
                startActivity(HomeIntent);
                finish();
            }
        },SPLASH_TIMEOUT);

    }
}
