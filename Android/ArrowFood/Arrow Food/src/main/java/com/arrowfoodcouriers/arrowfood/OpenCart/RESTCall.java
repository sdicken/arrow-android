package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class RESTCall extends AsyncTask<Object, Integer, String> {
    @Override
    protected String doInBackground(Object... objects) {
        URL url = (URL) objects[0];
        String body = (String) objects[1];
        ThisitaCookieManager cookieManager = (ThisitaCookieManager) objects[2];
        String response = "";
        try {
            // create the request
            URLConnection request = url.openConnection();
            // set cookies
            cookieManager.setCookies(request);
            // set POST method
            request.setDoOutput(true);
            request.setAllowUserInteraction(false);

            // Write data to POST
            PrintStream ps = new PrintStream(request.getOutputStream());
            ps.print(body);
            ps.close();

            request.connect();

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
