package com.arrowfoodcouriers.arrowfood.OpenCart;

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

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

/**
 * Android-based threading abstraction class used for POSTs.
 */
public class RealPOSTCall implements IRESTCall
{
    private final String _boundary = "----------f8n51w2QSEEMSYCsvNTHISftihLEGITodgfJ'";
    private final String _lineEnd = "\r\n";
    private final String _doubleHyphen = "--";

    public Boolean urlEncodeData = false;
    
    @SuppressWarnings("unchecked")
	public String makeRequestToServer(Object... objects)
    {
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
            if (urlEncodeData) {
                request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            } else {
                request.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + _boundary);
            }
            request.setDoOutput(true);
            request.setDoInput(true);

            // Write data to POST
            DataOutputStream dos = new DataOutputStream(request.getOutputStream());

            Set<String> keys = data.keySet();
            Iterator<String> keyIter = keys.iterator();
            String content = "";

            if (urlEncodeData) {
                for (int i = 0; keyIter.hasNext(); ++i) {
                    Object key = keyIter.next();

                    if (i != 0) {
                        content += "&";
                    }

                    content += URLEncoder.encode(key.toString(), "UTF-8");
                    content += "=";
                    content += URLEncoder.encode(data.get(key), "UTF-8");
                }
            } else {
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
