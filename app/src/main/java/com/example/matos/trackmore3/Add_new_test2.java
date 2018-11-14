package com.example.matos.trackmore3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_new_test2 extends AppCompatActivity {

    private EditText editName, editPin, editCode;
    private String name,pin,code;
    private Boolean pinBool = false,codeBool = false;
    SharedPreferences preferences;
    static boolean update = false, status;
    int clicks = 0;
    Dialog helpdialog;
    private View help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_test2);


        editName = findViewById(R.id.name);
        editPin = findViewById(R.id.pincode);
        editCode = findViewById(R.id.code);

        // For creating the popup menu when help is pressed
        helpdialog = new Dialog(this);


        editCode.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    update();
                    return true;
                }
                return false;
            }
        });
    }




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
            System.out.println(codeBool);
            System.out.println(pinBool);

            if(codeBool && pinBool){
            new AsyncCheck().execute(code);
                while(!update){
                    System.out.println("while");
                }

                if(status){
                    // check correct
                    update = false;
                    Toast.makeText(this, "Device Successfully Added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);

                }else {
                    // toast wrong ID
                    System.out.println("update false");
                    update = false;
                    Toast.makeText(this, "Failed to add Device, check verification code or pin", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void ShowPopup (View v) {
        TextView txtclose;
        helpdialog.setContentView(R.layout.help_popup_addnew);
        txtclose = (TextView) helpdialog.findViewById(R.id.close);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpdialog.dismiss();
            }
        });
        helpdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        helpdialog.show();
    }
}


