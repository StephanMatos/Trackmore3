package com.example.matos.trackmore3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {


    private Button tracking_button,  New_device, Setup_device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tracking_button = findViewById(R.id.tracking);
        New_device = findViewById(R.id.new_device);
        Setup_device = findViewById(R.id.setup);

        tracking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTrackingActivity();
            }
        });

        New_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDeviceActivity();
            }
        });

        Setup_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDevice();
            }
        });


    }

    public void startTrackingActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void addNewDeviceActivity(){
        Intent intent = new Intent(this, AddNewActivity.class);
        startActivity(intent);
    }

    public void setupDevice () {
        Intent intent = new Intent(this, Add_new_test2.class);
        startActivity(intent);
    }



}
