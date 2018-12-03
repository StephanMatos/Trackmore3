
package com.example.matos.trackmore3;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

public class SetupDevices extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_devices);


        ArrayList<JSONObject> JSONS = Services.load(this);
        SetupItemAdapter setupItemAdapter = new SetupItemAdapter(this, JSONS);
        listView = findViewById(R.id.listView);
        listView.setAdapter(setupItemAdapter);
    }
}
