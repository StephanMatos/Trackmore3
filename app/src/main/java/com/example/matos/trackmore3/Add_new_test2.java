package com.example.matos.trackmore3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Add_new_test2 extends AppCompatActivity {

    private EditText editName, editPin, editCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_test2);


        editName = findViewById(R.id.name);
        editPin = findViewById(R.id.pincode);
        editCode = findViewById(R.id.code);


        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.getText().clear();

            }
        });

        editPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPin.getText().clear();
            }
        });

        editCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCode.getText().clear();
            }
        });



    }





}
