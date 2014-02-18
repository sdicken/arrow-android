package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class OpenCartSession {
    public final String Server = "http://192.168.1.185/";
    public final String LoginRoute = "index.php?route=account/login";
    public final String RegisterRoute = "index.php?route=account/register";

    private ThisitaCookieManager _cookieManager;
    private String _email;
    private Boolean _authenticated;

    private String DoRequest(URL url, String body) throws IOException, ExecutionException, InterruptedException {
        RESTCall request = new RESTCall();
        request.execute(url, body, _cookieManager);
        return request.get();
    }

    public OpenCartSession() {
        _cookieManager = new ThisitaCookieManager();
        _email = "";
        _authenticated = false;
    }

    public String GetEmail() {
        return _email;
    }

    public Boolean IsAuthenticated() {
        return _authenticated;
    }

    public Boolean Login(String email, String password) {
        try {
            URL url = new URL(Server + LoginRoute);
            String json = "{\"email\": \"";
            json += email;
            json += "\", \"password\": \"";
            json += password;
            json += "\"}";
            DoRequest(url, json);
            _email = email;
            _authenticated = true;
            Log.d("Login", "Logged in");
            Log.d("Cookie", _cookieManager.toString());
            return true;
        } catch (Exception ex) {
            Log.e("Login", "Caught exception");
            return false;
        }
    }

    public Boolean Register(OpenCartRegistration registration) {
        if (!_authenticated || !registration.IsValid()) {
            return false;
        }
        try {
            URL url = new URL(Server + RegisterRoute);
            DoRequest(url, registration.GetJson());
            _email = registration.Email;
            _authenticated = true;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
