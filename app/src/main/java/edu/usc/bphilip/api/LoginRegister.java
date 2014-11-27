package edu.usc.bphilip.api;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijil_000 on 11/26/2014.
 */
public class LoginRegister extends AsyncTask<String, String, String> {
    /*
        Register new user or user's new device
     */
    protected String doInBackground(String... params) {

        String endpointURL = params[0];
        String userid = params[1];
        String deviceid = params[2];

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(endpointURL);

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userId", userid));
            nameValuePairs.add(new BasicNameValuePair("deviceId", deviceid));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Log.d("CollabParking-debug",responseString);
        }
        catch(Exception e){
            Log.d("CollabParking-debug", "Registration failed");
            return "";
        }
        return "";
    }
}
