package com.example.matos.trackmore3;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncCheck extends AsyncTask<String,Void,Boolean> {

    @Override
    protected Boolean doInBackground(String... strings) {

        System.out.println("Check START");
        JSONObject Master = new JSONObject();
        ArrayList<String> ID = new ArrayList<>();
        URL url = null;
        try {
            url = new URL("http://easyeats.dk/post.php?check=true&id="+strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            String data ="";

                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                    System.out.println(data);
                    httpURLConnection.disconnect();
                    if(data.contains("ID=TRUE")){
                        System.out.println("true");
                        return true;
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("GET END");
        return false;
    }

    @Override
    protected void onPostExecute(Boolean bool) {

    Add_new_test2.status = bool;
    Add_new_test2.update = true;
    }
}