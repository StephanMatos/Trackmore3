package com.example.matos.trackmore3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {

    private View help;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonDel, buttonNxt;
    private TextView txt1, txt2, txt3, txt4, txt5;
    int codeCount = 0;
    int btnPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        // All the buttons for the digit-pad
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonDel = findViewById(R.id.delButton);
        buttonNxt = findViewById(R.id.nxtbutton);

        // All the txtViews for the user's code
        txt1 = findViewById(R.id.Num1);
        txt2 = findViewById(R.id.Num2);
        txt3 = findViewById(R.id.Num3);
        txt4 = findViewById(R.id.Num4);
        txt5 = findViewById(R.id.Num5);

        //Clicklistners for all the buttons + Help view
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 1;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 2;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 3;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 4;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 5;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 6;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 7;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 8;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 9;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPress = 0;
                codeCount++;
                Code(btnPress, codeCount);
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codeCount > 5){
                    codeCount = 5;
                }
                Delete();
            }
        });
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next(codeCount);
            }
        });

    }
    // Setting the right txtView according to the pressed button
    public void Code(int btnPress, int codeCount){
        if (codeCount <= 5) {
            switch (codeCount) {
                case 1:
                    txt1.setText(Integer.toString(btnPress));
                    break;
                case 2:
                    txt2.setText(Integer.toString(btnPress));
                    break;
                case 3:
                    txt3.setText(Integer.toString(btnPress));
                    break;
                case 4:
                    txt4.setText(Integer.toString(btnPress));
                    break;
                case 5:
                    txt5.setText(Integer.toString(btnPress));
                    break;
                case 6:
                    codeCount = 5;
                    break;
            }
        } else {
            System.out.print("CODECOUNT: " + codeCount);


        }

    }

    // Deletes the dialled number if delete button is pressed
    public void Delete() {

        if (codeCount == 0) {
            return;
        } else {
            switch (codeCount) {
                case 1:
                    txt1.setText(""); break;
                case 2:
                    txt2.setText(""); break;
                case 3:
                    txt3.setText(""); break;
                case 4:
                    txt4.setText(""); break;
                case 5:
                    txt5.setText(""); break;
            }
            codeCount--;
        }

    }


    // Sends the code if Next button is pressed
    public String Next(int codeCount) {
        String code = "";

        if (codeCount < 5) {
            Toast.makeText(AddNewActivity.this, "Has to be 5 digits", Toast.LENGTH_LONG).show();
        } else {
            code = txt1.getText().toString() + txt2.getText().toString() + txt3.getText().toString() +
                    txt4.getText().toString() + txt5.getText().toString();
        }

        return code;
    }
}
