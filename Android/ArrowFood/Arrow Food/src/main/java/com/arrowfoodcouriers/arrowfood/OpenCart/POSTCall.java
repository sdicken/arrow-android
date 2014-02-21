package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class POSTCall extends AsyncTask<Object, Integer, String> {
    @Override
    protected String doInBackground(Object... objects) {
        URL url = (URL) objects[0];
        Map<String, String> data = (Map<String, String>) objects[1];
        ThisitaCookieManager cookieManager = (ThisitaCookieManager) objects[2];
        String response = "";
        try {
            // create the request
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            // set cookies
            cookieManager.setCookies(request);
            // set POST method
            request.setRequestMethod("POST");
            request.setDoOutput(true);
            request.setDoInput(true);

            // Write data to POST
            DataOutputStream dos = new DataOutputStream(request.getOutputStream());

            Set keys = data.keySet();
            Iterator keyIter = keys.iterator();
            String content = "";
            for (int i = 0; keyIter.hasNext(); ++i) {
                Object key = keyIter.next();
                if (i != 0) {
                    content += "&";
                }
                content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
            }
            dos.writeBytes(content);
            dos.flush();
            dos.close();

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
