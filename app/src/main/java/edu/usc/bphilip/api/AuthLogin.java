package edu.usc.bphilip.api;

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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijil_000 on 11/25/2014.
*/

public class AuthLogin {

    public String queryEndpoint(String... params) {
        String authEndpointURL = params[0];
        String userId = params[1];
        String password = params[2];


        //Log.d("CollabParking-debug", userId);
        //Log.d("CollabParking-debug", password);

        authEndpointURL += "/"+userId+"/authenticate";
        Log.d("Collabparking-Debug", authEndpointURL);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(authEndpointURL);

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONObject authJson = new JSONObject(responseString);
            String code1 = authJson.getString("code");
            String message1 = authJson.getString("message");
            if(code1.equals("200")){
                Log.d("Collabparking-Debug",message1);
                return "200";
            }
            else if(code1.equals("-401")){
                Log.d("Collabparking-Debug",message1);
                return "-401";
            }
            else if(code1.equals("-402")){
                Log.d("Collabparking-Debug",message1);
                return "-402";
            }
            else{
                Log.d("Collabparking-Debug",message1);
                return "-400";
            }
        }
        catch(Exception e){

        }

        return "";
    }

}


/*
public class AuthLogin extends AsyncTask<String, String, String> {

    protected String doInBackground(String... params) {
        String authEndpointURL = params[0];
        String userId = params[1];
        String password = params[2];
        String deviceId = params[3];
        String logInEndpointURL = params[0];

        authEndpointURL += "/"+userId+"/authenticate";
        Log.d("Collabparking-Debug", authEndpointURL);
        logInEndpointURL += "/login";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(authEndpointURL);

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONObject authJson = new JSONObject(responseString);
            String code1 = authJson.getString("code");
            String message1 = authJson.getString("message");
            if(code1.equals("200")){
                Log.d("Collabparking-Debug",message1);
                return "200";
            }
            else if(code1.equals("-401")){
                Log.d("Collabparking-Debug",message1);
                return "-401";
            }
            else if(code1.equals("-402")){
                Log.d("Collabparking-Debug",message1);
                return "-402";
            }
            else{
                Log.d("Collabparking-Debug",message1);
                return "-400";
            }
        }
        catch(Exception e){

        }

        return "";
    }

}
*/