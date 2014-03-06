package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by thisita on 2/18/14.
 * <p/>
 * Derived from http://www.hccp.org/java-net-cookie-how-to.html
 */
public class ThisitaCookieManager implements Parcelable{

    private Map<String, Map> store;

    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE_VALUE_DELIMITER = ";";
    public static final String PATH = "path";
    public static final String EXPIRES = "expires";
    public static final String DATE_FORMAT = "EEE, dd-MMM-yyyy hh:mm:ss z";
    public static final String SET_COOKIE_SEPARATOR = "; ";
    public static final String COOKIE = "Cookie";

    public static final char NAME_VALUE_SEPARATOR = '=';
    public static final char DOT = '.';

    private DateFormat dateFormat;

    public ThisitaCookieManager() {
        store = new HashMap<String, Map>();
        dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    private ThisitaCookieManager(Parcel in)
    {
        store = new HashMap<String, Map>();
        in.readMap(store, getClass().getClassLoader());
        dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    public void storeCookies(URLConnection conn) throws IOException {

        // let's determine the domain from where these cookies are being sent
        String domain = getDomainFromHost(conn.getURL().getHost());


        Map<String, Map<String, String>> domainStore; // this is where we will store cookies for this domain

        // now let's check the store to see if we have an entry for this domain
        if (store.containsKey(domain)) {
            // we do, so lets retrieve it from the store
            domainStore = (Map<String, Map<String, String>>) store.get(domain);
        } else {
            // we don't, so let's create it and put it in the store
            domainStore = new HashMap<String, Map<String, String>>();
            store.put(domain, domainStore);
        }


        // OK, now we are ready to get the cookies out of the URLConnection

        String headerName;
        for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equalsIgnoreCase(SET_COOKIE)) {
                Map<String, String> cookie = new HashMap<String, String>();
                StringTokenizer st = new StringTokenizer(conn.getHeaderField(i), COOKIE_VALUE_DELIMITER);

                // the specification dictates that the first name/value pair
                // in the string is the cookie name and value, so let's handle
                // them as a special case:

                if (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    String name = token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR));
                    String value = token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1, token.length());
                    domainStore.put(name, cookie);
                    cookie.put(name, value);
                }

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    cookie.put(token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR)).toLowerCase(Locale.US),
                            token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1, token.length()));
                }
            }
        }
    }


    /**
     * Prior to opening a URLConnection, calling this method will set all
     * unexpired cookies that match the path or subpaths for thi underlying URL
     * <p/>
     * The connection MUST NOT have been opened
     * method or an IOException will be thrown.
     *
     * @param conn a java.net.URLConnection - must NOT be open, or IOException will be thrown
     * @throws java.io.IOException Thrown if conn has already been opened.
     */
    public void setCookies(URLConnection conn) throws IOException {

        // let's determine the domain and path to retrieve the appropriate cookies
        URL url = conn.getURL();
        String domain = getDomainFromHost(url.getHost());
        String path = url.getPath();

        Map<?, ?> domainStore = (Map<?, ?>) store.get(domain);
        if (domainStore == null) return;
        StringBuilder cookieStringBuffer = new StringBuilder();

        Iterator<?> cookieNames = domainStore.keySet().iterator();
        while (cookieNames.hasNext()) {
            String cookieName = (String) cookieNames.next();
            Map<?, ?> cookie = (Map<?, ?>) domainStore.get(cookieName);
            // check cookie to ensure path matches  and cookie is not expired
            // if all is cool, add cookie to header string
            if (comparePaths((String) cookie.get(PATH), path) && isNotExpired((String) cookie.get(EXPIRES))) {
                cookieStringBuffer.append(cookieName);
                cookieStringBuffer.append("=");
                cookieStringBuffer.append((String) cookie.get(cookieName));
                if (cookieNames.hasNext()) cookieStringBuffer.append(SET_COOKIE_SEPARATOR);
            }
        }
        try {
            conn.setRequestProperty(COOKIE, cookieStringBuffer.toString());
        } catch (java.lang.IllegalStateException ise) {
            throw new IOException("Illegal State! Cookies cannot be set on a URLConnection that is already connected. "
                    + "Only call setCookies(java.net.URLConnection) AFTER calling java.net.URLConnection.connect().");
        }
    }

    private String getDomainFromHost(String host) {
        if (host.indexOf(DOT) != host.lastIndexOf(DOT)) {
            return host.substring(host.indexOf(DOT) + 1);
        } else {
            return host;
        }
    }

    private boolean isNotExpired(String cookieExpires) {
        if (cookieExpires == null) return true;
        Date now = new Date();
        try {
            return (now.compareTo(dateFormat.parse(cookieExpires))) <= 0;
        } catch (java.text.ParseException pe) {
            pe.printStackTrace();
            return false;
        }
    }

    private boolean comparePaths(String cookiePath, String targetPath) {
        return cookiePath == null || cookiePath.equals("/")
                || targetPath.regionMatches(0, cookiePath, 0, cookiePath.length());
    }

    /**
     * Returns a string representation of stored cookies organized by domain.
     */

    public String toString() {
        return store.toString();
    }

    // needed when app killed in background because system needs more memory
    public static final Creator<ThisitaCookieManager> CREATOR = new Creator<ThisitaCookieManager>()
    {
        public ThisitaCookieManager createFromParcel(Parcel in)
        {
            return new ThisitaCookieManager(in);
        }

        public ThisitaCookieManager[] newArray(int size) {
            return new ThisitaCookieManager[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeMap(store);
    }
}
