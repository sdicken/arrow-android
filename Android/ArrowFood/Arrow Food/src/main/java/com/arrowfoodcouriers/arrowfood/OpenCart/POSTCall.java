package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.arrowfoodcouriers.arrowfood.LoginDialogCallback;

public class POSTCall extends AsyncTask<Object, Integer, String>
{
    private OpenCartTask _task;
    private RESTCallback _RESTCallback;
    private LoginDialogCallback _loginDialogCallback;

    public POSTCall(OpenCartTask task, RESTCallback listener, LoginDialogCallback loginDialogCallback)
    {
        _task = task;
        _RESTCallback = listener;
        _loginDialogCallback = loginDialogCallback;
    }
    private final String _boundary = "----------f8n51w2QSEEMSYCsvNTHISftihLEGITodgfJ'";
    private final String _lineEnd = "\r\n";
    private final String _doubleHyphen = "--";

    @Override
    protected void onPreExecute() {
        _loginDialogCallback.onTaskStart();
    }

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
            request.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + _boundary);
            request.setDoOutput(true);
            request.setDoInput(true);

            // Write data to POST
            DataOutputStream dos = new DataOutputStream(request.getOutputStream());

            Set keys = data.keySet();
            Iterator keyIter = keys.iterator();
            String content = "";
            for (int i = 0; keyIter.hasNext(); ++i) {
                Object key = keyIter.next();

                content += _doubleHyphen + _boundary + _lineEnd
                        + "Content-Disposition: form-data; name=\""
                        + key + "\"" + _lineEnd;
                content += _lineEnd;
                content += data.get(key);
                content += _lineEnd;
                }
            content += _doubleHyphen + _boundary + _doubleHyphen;
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

    @Override
    protected void onPostExecute(String response) {
        _RESTCallback.onTaskCompleted(_task, response);
    }
}
