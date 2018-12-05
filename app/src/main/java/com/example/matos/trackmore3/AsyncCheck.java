package com.example.matos.trackmore3;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncCheck extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {

        System.out.println("Check START");
        URL url = null;
        try {
            url = new URL("http://easyeats.dk/post.php?check=true&id="+strings[0]+"&pin="+strings[1]);
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
                    if(data.contains("ID=TRUE") && data.contains("PIN=TRUE")){
                        System.out.println("true");
                        Add_new_test2.update = true;
                        Add_new_test2.status = true;

                    }else{
                        Add_new_test2.update = true;
                        Add_new_test2.status = false;
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("GET END");
        return null;
    }

}