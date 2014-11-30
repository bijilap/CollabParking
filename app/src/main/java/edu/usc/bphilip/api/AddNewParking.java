package edu.usc.bphilip.api;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijil_000 on 11/26/2014.
 */
public class AddNewParking extends AsyncTask<String, String, String> {
    protected String doInBackground(String... params) {
        String endpointURL = params[0];
        String type = params[1];
        if(type.equals("add")){
            String name = params[2];
            String pricePerDay = params[3];
            String pricePerHour = params[4];
            String capacity = params[5];
            String location = params[6];
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(endpointURL);

            try{
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                /*
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("location", location));
                nameValuePairs.add(new BasicNameValuePair("pricePerHour", pricePerHour));
                nameValuePairs.add(new BasicNameValuePair("pricePerDay", pricePerDay));
                nameValuePairs.add(new BasicNameValuePair("capacity", capacity));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                */

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("name", name);
                jsonObject.accumulate("location", location);
                jsonObject.accumulate("pricePerDay", pricePerDay);
                jsonObject.accumulate("pricePerHour", pricePerHour);
                jsonObject.accumulate("capacity", capacity);
                Log.d("Form", jsonObject.toString());
                StringEntity se = new StringEntity(jsonObject.toString());
                httppost.setEntity(se);
                httppost.setHeader("Accept", "application/json");
                httppost.setHeader("Content-type", "application/json");

                HttpResponse response = httpclient.execute(httppost);
                Log.d("Form", EntityUtils.toString(response.getEntity()));


            }
            catch(Exception e){
                Log.d("Form", "Form failed");
            }

        }
        return "";
    }
}
