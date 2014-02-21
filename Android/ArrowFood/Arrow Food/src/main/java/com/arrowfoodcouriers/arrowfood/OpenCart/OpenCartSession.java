package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class OpenCartSession implements RESTCallback{
    public final Boolean DEBUG = true;

    public final String Server = "http://192.168.1.185/";
    public final String LoginRoute = "index.php?route=account/login";
    public final String RegisterRoute = "index.php?route=account/register";
    public final String EditAccountRoute = "index.php?route=account/edit";
    public final String LogoutRoute = "index.php?route=account/logout"; // TODO: Check me!
    public final String OrderRoute = "index.php?route=order/checkout"; // TODO: Check me!

    private ThisitaCookieManager _cookieManager;
    private String _email;
    private Boolean _authenticated;

    private String _firstName;
    private String _lastName;
    private String _telephone;

    private void DoPOST(OpenCartTask task, URL url, Map<String, String> data) throws IOException, ExecutionException, InterruptedException {
        POSTCall request = new POSTCall(task, this);
        request.execute(url, data, _cookieManager);
//        return request.get();
    }

    private void DoGET(URL url) throws IOException, ExecutionException, InterruptedException {
        GETCall request = new GETCall();
        request.execute(url, _cookieManager);
//        return request.get();
    }

    private final Integer FirstNameLine = 156;
    private final Integer LastNameLine = 161;
    private final Integer TelephoneLine = 171;
    private final Integer FormControlEndCharOffset = 4;

    private void ParseEditHTML(String html) {
        String firstName, lastName, phone;
        String[] lines = html.split("\n");

        firstName = lines[FirstNameLine];
        lastName = lines[LastNameLine];
        phone = lines[TelephoneLine];

        firstName = firstName.substring(firstName.indexOf("value=\""), firstName.length() - FormControlEndCharOffset);
        lastName = lastName.substring(lastName.indexOf("value=\""), lastName.length() - FormControlEndCharOffset);
        phone = phone.substring(phone.indexOf("value=\""), phone.length() - FormControlEndCharOffset);

        _firstName = firstName;
        _lastName = lastName;
        _telephone = phone;
    }

    public OpenCartSession() {
        _cookieManager = new ThisitaCookieManager();
        _authenticated = false;
        try {
//            Log.d("Getting index so PHP knows who we are", DoGET(new URL(Server)));
            DoGET(new URL(Server));
        } catch (Exception ex) {
            Log.d("EXCEPTION:", ex.toString());
        }
    }

    public String GetEmail() {
        return _email;
    }

    public String GetFirstName() {
        return _firstName;
    }

    public String GetLastName() {
        return _lastName;
    }

    public String GetTelephone() {
        return _telephone;
    }

    public Boolean IsAuthenticated() {
        return _authenticated;
    }

    public Boolean Login(String email, String password) {
        try {
            URL url = new URL(Server + LoginRoute);
            String em = DEBUG ? "e674501@drdrb.com" : email;
            String pa = DEBUG ? "pass" : password;
            Map<String, String> data = new HashMap<String, String>();
            data.put("email", em);
            data.put("password", pa);
            DoPOST(OpenCartTask.LOGIN, url, data);
            _email = email;
//            _authenticated = true;
//            Log.d("Login", "Logged in");
//            Log.d("Cookie", _cookieManager.toString());
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
            // TODO: Fix
            //DoPOST(url, registration.GetJson());
            _email = registration.Email;
//            _authenticated = true;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Logout() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + LogoutRoute);
            DoPOST(OpenCartTask.LOGOUT, url, new HashMap<String, String>());
            _email = null;
//            _authenticated = false;
        } catch (Exception ex) {
            Log.e("Logout()", "Caught exception!");
        }
    }

    public Boolean Order(OpenCartOrder order) {
        if (!_authenticated || !order.IsValid()) {
            return false;
        }
        try {
            URL url = new URL(Server + OrderRoute);
            // TODO: Fix
            //DoPOST(url, order.GetJson());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void LoadUserData() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + EditAccountRoute);
            DoGET(url);
//            String response = DoGET(url);
//            ParseEditHTML(response);
        } catch (Exception ex) {
            Log.e("LoadUserData()", "Caught exception!");
        }
    }

    @Override
    public void onTaskCompleted(OpenCartTask task, String response) {
        switch(task)
        {
            case ORDER:
            {
                // nothing to do
                break;
            }
            case LOGOUT:
            {
                _authenticated = false;
                break;
            }
            case REGISTER:
            {
                _authenticated = true;
                break;
            }
            case LOGIN:
            {
                _authenticated = true;
                Log.d("Login", "Logged in");
                Log.d("Cookie", _cookieManager.toString());
                break;
            }
            case USER_DATA_LOADED:
            {
                ParseEditHTML(response);
                break;
            }
        }
        Log.d("Response", response);
    }
}
