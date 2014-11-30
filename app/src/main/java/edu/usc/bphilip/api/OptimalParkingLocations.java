package edu.usc.bphilip.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bijil_000 on 11/29/2014.
 */
public class OptimalParkingLocations extends AsyncTask<String, String, String> {
    protected String doInBackground(String... params) {
        /*
            params[0] is the url
            params[1] is  the userid
            params[2] is the count
         */
        String endpointURL = params[0];
        String userid = params[1];
        String count = params[2];
        String argname = params[3];

        try{
            endpointURL += "?userid="+userid+"&"+argname+"="+count;
            URL url = new URL(endpointURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder tmpResult = new StringBuilder();
            String line = null;
            while((line=buf.readLine())!=null){
                tmpResult.append(line);
            }
            String resultJSON = tmpResult.toString();
            //log entries
            Log.d("MyApp", "Recieved the JSON of parking locations");
            Log.d("MyApp", resultJSON);
            //end
            return  resultJSON;

        }
        catch(Exception e){
            Log.d("MyApp", e.getMessage());
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
