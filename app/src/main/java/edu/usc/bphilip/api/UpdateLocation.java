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
import java.util.Calendar;
import java.util.List;

/**
 * Created by bijil_000 on 11/28/2014.
 */
public class UpdateLocation extends AsyncTask<String, String, String> {
    protected String doInBackground(String... params) {
        String endpointURL = params[0];
        String latitude = params[1];
        String longitude = params[2];
        // example url : http://54.69.152.156:55321/csp/data/user/1/location

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(endpointURL);
        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("timestamp", now.getTime()+""));
            nameValuePairs.add(new BasicNameValuePair("location", "POINT("+longitude+" "+latitude+")"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            Log.d("LocationUpdate",now.getTime()+"");
            Log.d("LocationUpdate","POINT("+longitude+" "+latitude+")");
            Log.d("LocationUpdate","Successfully update location");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
