package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class OpenCartSession {
    public final String Server = "https://192.168.1.185/";
    public final String LoginRoute = "index.php?route=account/login";
    private ThisitaCookieManager _cookieManager;
    private String _email;


    private String DoRequest(URL url, String body) throws IOException {
        // create the request
        URLConnection request = url.openConnection();
        // set cookies
        _cookieManager.setCookies(request);
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
        String response = "";
        while ((line = br.readLine()) != null) {
            response += line;
            response += '\n';
        }
        br.close();

        // Remove the last newline
        if (response.length() > 0) {
            response = response.substring(0, response.length() - 1);
        }

        _cookieManager.storeCookies(request);

        return response;
    }

    public OpenCartSession() {
        _cookieManager = new ThisitaCookieManager();
        _email = "";
    }

    public String GetEmail() {
        return _email;
    }

    public void Login(String email, String password) {
        try {
            URL url = new URL(Server + LoginRoute);
            String json = "{\"email\": \"" + email
                    + "\", \"password\": \"" + password
                    + "\"}";
            String response = DoRequest(url, json);
        } catch (Exception ex) {

        }
    }
}
