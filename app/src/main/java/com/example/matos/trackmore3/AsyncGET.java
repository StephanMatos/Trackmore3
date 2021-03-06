package com.example.matos.trackmore3;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncGET extends AsyncTask<String,Void,JSONObject> {


    @Override
    protected JSONObject doInBackground(String... strings) {
        System.out.println("GET");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject Master = new JSONObject();

        try {

            for(int i = 0; i < MapsActivity.ID.size(); i++){
                String url1 = "http://easyeats.dk/post.php?check=false&id="+MapsActivity.ID.get(i)+"&pin="+MapsActivity.PIN.get(i);
                URL url = new URL(url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String hexa;
                String line = "";
                String data ="";

                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                httpURLConnection.disconnect();
                System.out.print("Data is:       ");
                System.out.println(data);

                // Creates a JSON object from the text read from URL
                JSONObject json;
                json = new JSONObject(data);
                hexa = json.getString("data");

                StringBuilder sb = new StringBuilder();
                char[] hexData = hexa.toCharArray();
                for (int count = 0; count < hexData.length - 1; count += 2) {
                    int firstDigit = Character.digit(hexData[count], 16);
                    int lastDigit = Character.digit(hexData[count + 1], 16);
                    int decimal = firstDigit * 16 + lastDigit;
                    sb.append((char)decimal);
                }
                System.out.println(sb.toString());
                Master.put(MapsActivity.Name.get(i),sb.toString());

            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        System.out.println("GET END");
        System.out.println(Master);
        return Master;
    }

    @Override
    protected void onPostExecute(JSONObject object) {

    MapsActivity.updateJson(object);

    }
}