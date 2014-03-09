package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.Interfaces.LoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.NavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.RESTCallback;

public class OpenCartSession implements RESTCallback, ISession, Parcelable{
    public final static Boolean DEBUG = true;

    public static final String Server = "http://192.168.1.185/";
    public static final String LoginRoute = "index.php?route=account/login";
    public static final String RegisterRoute = "index.php?route=account/register";
    public static final String EditAccountRoute = "index.php?route=account/edit";
    public static final String AddItemRoute = "index.php?route=checkout/cart/add";
    public static final String LogoutRoute = "index.php?route=account/logout";
    public static final String CheckoutRoute = "index.php?route=checkout/cart";
    public static final String CountryRoute = "index.php?route=checkout/cart/country";
    public static final String OrderRoute = "index.php?route=order/checkout"; // TODO: Check me!

    private ThisitaCookieManager _cookieManager;
    private String _email;
    private Boolean _authenticated;
    private LoginDialogCallback _loginDialogCallback;
    private NavigationDrawerCallback _navigationDrawerCallback;

    private String _firstName;
    private String _lastName;
    private String _telephone;

    // POST without urlEncode
    private void DoPOST(OpenCartTask task, URL url, Map<String, String> data) throws IOException, ExecutionException, InterruptedException {
        POSTTask request = new POSTTask(task, this, _loginDialogCallback, DEBUG ? new MockRESTCall() : new RealPOSTCall());
        request.execute(url, data, _cookieManager);
    }

    // POST with urlEncode
    private void DoPOST(OpenCartTask task, URL url, Map<String, String> data, Boolean urlEncode) throws IOException, ExecutionException, InterruptedException {
        POSTTask request = new POSTTask(task, this, _loginDialogCallback, DEBUG ? new MockRESTCall() : new RealPOSTCall());
        request.urlEncodeData = urlEncode;
        request.execute(url, data, _cookieManager);
    }

    private void DoGET(OpenCartTask task, URL url) throws IOException, ExecutionException, InterruptedException {
        GETTask request = new GETTask(task, this, DEBUG ? new MockRESTCall() : new RealGETCall());
        request.execute(url, _cookieManager);
    }

    // line numbers for parsing method
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
            DoGET(OpenCartTask.CONSTRUCTOR, new URL(Server));
        } catch (Exception ex) {
            Log.d("EXCEPTION:", ex.toString());
        }
    }

    private OpenCartSession(Parcel in)
    {
        _authenticated = in.readByte() != 0;    // true if byte != 0 (no readBoolean method)
        _cookieManager = in.readParcelable(getClass().getClassLoader());
    }

    public void AttachLoginDialogCallback(LoginDialogCallback loginDialogCallback)
    {
        _loginDialogCallback = loginDialogCallback;
    }

    public void AttachNavigationDrawerCallback(NavigationDrawerCallback navigationDrawerCallback)
    {
        _navigationDrawerCallback = navigationDrawerCallback;
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

    public ThisitaCookieManager GetCookieManager()
    {
        return _cookieManager;
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
            DoPOST(OpenCartTask.LOGIN, url, data);
            _email = email;
            return true;
        } catch (Exception ex) {
            Log.e("Login", "Caught exception");
            return false;
        }
    }

    public Boolean Register(OpenCartRegistration registration) {
        if (_authenticated || !registration.IsValid()) {
            return false;
        }
        try {
            URL url = new URL(Server + RegisterRoute);
            DoPOST(OpenCartTask.REGISTER, url, registration.GetData());
            _email = registration.Email;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Logout() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + LogoutRoute);
            DoGET(OpenCartTask.LOGOUT, url);
            _email = null;
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
            DoPOST(OpenCartTask.ADD_TO_CART, url, item.GetData(), true);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void LoadUserData() {
        if (!_authenticated) return;

        try {
            URL url = new URL(Server + EditAccountRoute);
            DoGET(OpenCartTask.USER_DATA_LOADED, url);
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
            DoPOST(OpenCartTask.APPLY_COUPON, url, data);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Boolean ApplyVoucher(String voucher) {
        try {
            URL url = new URL(Server + CheckoutRoute);
            Map<String, String> data = new HashMap<String, String>();
            data.put("voucher", voucher);
            data.put("next", "voucher");
            DoPOST(OpenCartTask.APPLY_VOUCHER, url, data);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

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
                _navigationDrawerCallback.onNavigationDrawerUpdated();
                break;
            }
            case REGISTER:
            {
//                _authenticated = true;
                break;
            }
            case LOGIN:
            {
                _authenticated = true;
                _loginDialogCallback.onTaskCompleted(); // TODO: actually determine if login succeeded/failed
                if(_authenticated) 
                { 
                	_loginDialogCallback.onSuccess(); 
                	_navigationDrawerCallback.onNavigationDrawerUpdated();
                }
                else { _loginDialogCallback.onFailure(); }
                break;
            }
            case USER_DATA_LOADED:
            {
                ParseEditHTML(response);
                break;
            }
            case CONSTRUCTOR:
            {
                // no action necessary
                break;
            }
            case ADD_TO_CART:
            {
                break;
            }
            case APPLY_VOUCHER:
            {
                break;
            }
            case APPLY_COUPON:
            {
                break;
            }
        }
    }

    // needed when app killed in background because system needs memory
    public static final Creator<OpenCartSession> CREATOR = new Creator<OpenCartSession>()
    {
        public OpenCartSession createFromParcel(Parcel in)
        {
            return new OpenCartSession(in);
        }

        public OpenCartSession[] newArray(int size) {
            return new OpenCartSession[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeByte((byte) (_authenticated ? 1 : 0)); // if _authenticated true, byte == 1 (no writeBoolean method)
        out.writeParcelable(_cookieManager, 0);
        // Android reads/writes into internal map using reflection
        // source: https://stackoverflow.com/questions/4853952/android-parcelable-writetoparcel-and-parcelable-creator-createfromparcel-are-ne
    }
}
