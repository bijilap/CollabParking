package edu.usc.bphilip.api;

import android.os.AsyncTask;

/**
 * Created by bijil_000 on 11/25/2014.
 */
public class AuthLogin extends AsyncTask<String, String, String> {

    protected String doInBackground(String... params) {
        String authEndpointURL = params[0];
        String userId = params[1];
        String password = params[2];
        return "";
    }

}
