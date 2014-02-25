package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Android-based threading abstraction class used for GETs.
 */
public class GETCall extends AsyncTask<Object, Integer, String>
{
    public String accept = null;
    private OpenCartTask _task;
    private RESTCallback _RESTCallback;

    /**
     *
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     */
    public GETCall(OpenCartTask task, RESTCallback restCallback)
    {
        _task = task;
        _RESTCallback = restCallback;
    }

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
            // Set accept header
            if (accept != null) request.setRequestProperty("Accept", accept);

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

    @Override
    protected void onPostExecute(String response) {
        _RESTCallback.onTaskCompleted(_task, response);
    }
}
