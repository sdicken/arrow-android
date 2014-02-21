package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GETCall extends AsyncTask<Object, Integer, String> {
    @Override
    protected String doInBackground(Object... objects) {
        URL url = (URL) objects[0];
        ThisitaCookieManager cookieManager = (ThisitaCookieManager) objects[1];
        String response = "";
        try {
            // create the request
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            // set cookies
            cookieManager.setCookies(request);

            // read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                response += line;
                response += '\n';
            }
            br.close();

            // Remove the last newline
            if (response.length() > 0) {
                response = response.substring(0, response.length() - 1);
            }

            cookieManager.storeCookies(request);

        } catch (IOException ex) {
            return "Error in RESTCall execution";
        }
        return response;
    }
}
