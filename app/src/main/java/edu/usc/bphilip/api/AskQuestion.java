package edu.usc.bphilip.api;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijil_000 on 11/28/2014.
 */
public class AskQuestion extends AsyncTask<String, String, String> {
    protected String doInBackground(String... params) {
        String endpointURL = params[0];
        String parkingLot = params[1];
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(endpointURL);

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("parking_lot", parkingLot));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
        }
        catch(Exception e){
            Log.d("Broadcast-Q", "Broadcasting failed");
        }

        return "";
    }
}
