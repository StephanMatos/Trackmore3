package com.example.matos.trackmore3;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Services {


    public static boolean save(Context c, JSONObject json) {

        try {
            OutputStreamWriter o = new OutputStreamWriter(c.openFileOutput("file.txt", Context.MODE_PRIVATE));

            o.append("#" + json.toString());

            System.out.println("SAVE " + json.toString());

            o.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static ArrayList<JSONObject> load(Context c) {

        ArrayList<JSONObject> JSONS = new ArrayList<>();
        String result = "";

        try {
            InputStream inputStream = c.openFileInput("file.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                result = stringBuilder.toString();
                System.out.println("LOAD " + result);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] ss = result.split("#");

        for(int i = 1; i < ss.length; i++){
            try {
                JSONObject json = new JSONObject(ss[i]);
                JSONS.add(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return JSONS;

    }

}
