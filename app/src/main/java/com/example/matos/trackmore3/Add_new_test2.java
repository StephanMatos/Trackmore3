package com.example.matos.trackmore3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Add_new_test2 extends AppCompatActivity {

    private EditText editName, editPin, editCode;
    private String name,pin,code;
    private Boolean pinBool = false,codeBool = false;
    SharedPreferences preferences;
    static boolean update = false, status;

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
               setTexts();
           }
       });

        editPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCode.getText().clear();
            }
        });

    public void setTexts() {


        editName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ( (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editPin.getText().clear();
                    editName.clearFocus();
                    System.out.println("sadfghjkhgfdfghjklkjhgfdcvbnmk,lkjhgfd");
                    editPin.requestFocus();
                    return true;
                }
                return false;
            }
        });

    private void update(){

        if(true){
            name = String.valueOf(editName.getText());
            pin = String.valueOf(editPin.getText());
            code = String.valueOf(editCode.getText());
            if(pin.length() != 4){
                // make toast telling pin should contain 4 digits

            }else{
                pinBool = true;
            }
            if(code.length() != 5){
                // make toast telling pin should contain 5 digits

            } else {
                codeBool = true;
            }

            if(codeBool && pinBool){
            new AsyncCheck().execute();
                while(!update){

                }
                if(status){
                    // check corret
                    update = false;
                }else {
                    // toast wrong ID

                    update = false;
                }

            }

        }

    }




    }

}
