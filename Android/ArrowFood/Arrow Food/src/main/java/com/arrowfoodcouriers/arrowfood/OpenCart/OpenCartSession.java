package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class OpenCartSession {
    public final Boolean DEBUG = true;

    public final String Server = "http://192.168.1.185/";
    public final String LoginRoute = "index.php?route=account/login";
    public final String RegisterRoute = "index.php?route=account/register";
    public final String EditAccountRoute = "index.php?route=account/edit";
    public final String AddItemRoute = "index.php?route=checkout/cart/add";
    public final String LogoutRoute = "index.php?route=account/logout";
    public final String CheckoutRoute = "index.php?route=checkout/cart";
    public final String CountryRoute = "index.php?route=checkout/cart/country";
    public final String OrderRoute = "index.php?route=order/checkout"; // TODO: Check me!

    private ThisitaCookieManager _cookieManager;
    private String _email;
    private Boolean _authenticated;

    private String _firstName;
    private String _lastName;
    private String _telephone;

    private String DoPOST(URL url, Map<String, String> data) throws IOException, ExecutionException, InterruptedException {
        POSTCall request = new POSTCall();
        request.execute(url, data, _cookieManager);
        return request.get();
    }

    private String DoPOST(URL url, Map<String, String> data, Boolean urlEncode) throws IOException, ExecutionException, InterruptedException {
        POSTCall request = new POSTCall();
        request.urlEncodeData = urlEncode;
        request.execute(url, data, _cookieManager);
        return request.get();
    }

    private String DoGET(URL url) throws IOException, ExecutionException, InterruptedException {
        GETCall request = new GETCall();
        request.execute(url, _cookieManager);
        return request.get();
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
            Log.d("Getting index so PHP knows who we are", DoGET(new URL(Server)));
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
            String em = DEBUG ? "test@test.test" : email;
            String pa = DEBUG ? "test" : password;
            Map<String, String> data = new HashMap<String, String>();
            data.put("email", em);
            data.put("password", pa);
            DoPOST(url, data);
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
            DoPOST(url, registration.GetData());
            _email = registration.Email;
            _authenticated = true;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Logout() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + LogoutRoute);
            DoGET(url);
            _email = null;
            _authenticated = false;
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

    public Boolean AddToCart(OpenCartItem item) {
        try {
            URL url = new URL(Server + AddItemRoute);
            // Needs to do a URLEncoded POST
            // option[134]=kljsfjkfd&option[234]=lkjfi3&quatity=2&product_id=42
            String response = DoPOST(url, item.GetData(), true);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void LoadUserData() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + EditAccountRoute);
            String response = DoGET(url);
            ParseEditHTML(response);
        } catch (Exception ex) {
            Log.e("LoadUserData()", "Caught exception!");
        }
    }

    public Boolean ApplyCoupon(String coupon) {
        try {
            URL url = new URL(Server + CheckoutRoute);
            Map<String, String> data = new HashMap<String, String>();
            data.put("coupon", coupon);
            data.put("next", "coupon");
            String response = DoPOST(url, data);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
