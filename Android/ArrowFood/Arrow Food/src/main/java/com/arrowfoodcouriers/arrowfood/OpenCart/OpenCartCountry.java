package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.util.ArrayList;

/**
 * Created by thisita on 2/22/14.
 */
public class OpenCartCountry {
    public String CountryId;
    public String Name;
    public String ISOCode2;
    public String ISOCode3;
    public String AddressFormat;
    public String PostCodeRequired;
    public ArrayList<OpenCartZone> Zones;

    public OpenCartCountry() {
    }

    public OpenCartCountry(String countryId) {
        // TODO: add logic to automagical GET the data
    }
}
