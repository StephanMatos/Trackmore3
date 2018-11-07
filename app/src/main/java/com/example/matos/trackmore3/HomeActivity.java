package com.example.matos.trackmore3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {


    private Button tracking_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tracking_button = findViewById(R.id.tracking);

        tracking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTrackingActivity();
            }
        });


    }


    public void startTrackingActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }




}
