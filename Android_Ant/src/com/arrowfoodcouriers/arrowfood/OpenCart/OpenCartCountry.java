package com.arrowfoodcouriers.arrowfood.OpenCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class OpenCartCountry {
    public String CountryId;
    public String Name;
    public String ISOCode2;
    public String ISOCode3;
    public String AddressFormat;
    public String PostCodeRequired;
    public ArrayList<OpenCartZone> Zones;

    private void parseJson(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        Name = obj.getString("name");
        ISOCode2 = obj.getString("iso_code_2");
        ISOCode3 = obj.getString("iso_code_3");
        AddressFormat = obj.getString("address_format");
        PostCodeRequired = obj.getString("0");
        JSONArray zones = obj.getJSONArray("zone");
        for (int i = 0, l = zones.length(); i < l; ++i) {
            OpenCartZone zone = new OpenCartZone();
            JSONObject zoneObj = zones.getJSONObject(i);
            zone.ZoneId = zoneObj.getString("zone_id");
            zone.CountryId = zoneObj.getString("country_id");
            zone.Name = zoneObj.getString("name");
            zone.Code = zoneObj.getString("code");
            zone.Status = zoneObj.getString("status");
            Zones.add(zone);
        }
    }

    public OpenCartCountry(String countryId, OpenCartSession session)
            throws MalformedURLException, ExecutionException, InterruptedException, JSONException {
        CountryId = countryId;
        Zones = new ArrayList<OpenCartZone>();
        GETTask request = new GETTask(OpenCartTask.COUNTRY, session, new RealGETCall());
        String accept = "application/json, text/javascript, */*; q=0.01";
        URL url = new URL(OpenCartSession.Server + OpenCartSession.CountryRoute + "&country=" + CountryId);
        request.execute(url, session.GetCookieManager(), accept);
        String json = request.get();
        parseJson(json);
    }
}
