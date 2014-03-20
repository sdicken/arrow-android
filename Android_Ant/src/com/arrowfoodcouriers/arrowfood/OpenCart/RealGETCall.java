package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.arrowfoodcouriers.arrowfood.Interfaces.ICookieManager;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

public class RealGETCall implements IRESTCall 
{
	public RealGETCall()
	{
		
	}

	public String makeRequestToServer(Object... objects) 
	{
		URL url = (URL) objects[0];
        ICookieManager cookieManager = (ThisitaCookieManager) objects[1];
        String response = "";
        String accept = (String) objects[2];
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
}
