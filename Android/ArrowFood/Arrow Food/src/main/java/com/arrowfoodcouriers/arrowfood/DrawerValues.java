package com.arrowfoodcouriers.arrowfood;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginClass;

/**
 * Created by Ryan on 2/6/14.
 */
public class DrawerValues {
    private static final Object[] DRAWER_VALUES_LOGGED_IN = {
            new DrawerListObject("Home", 0),
            new DrawerListObject("Restaurants", 1),
            new DrawerListObject("Food by Type", 2),
            "Account",
            new DrawerListObject("Profile", 3),
            new DrawerListObject("Previous Orders", 5),
            new DrawerListObject("Favorites", 6),
            "More",
            new DrawerListObject("Areas Served", 7),
            new DrawerListObject("About Us", 8),
            new DrawerListObject("Sign Out", 9)
    };

    private static final Object[] DRAWER_VALUES_LOGGED_OUT = {
            new DrawerListObject("Home", 0),
            new DrawerListObject("Restaurants", 1),
            new DrawerListObject("Food by Type", 2),
            "Account",
            new DrawerListObject("Login", 4),
            "More",
            new DrawerListObject("Areas Served", 7),
            new DrawerListObject("About Us", 8)
    };

    private ILoginClass _loginClass;

    public DrawerValues(ILoginClass loginClass)
    {
        this._loginClass = loginClass;
    }

    public Object[] getDrawerValues() {
        return (_loginClass.IsUserLoggedIn()) ? DRAWER_VALUES_LOGGED_IN : DRAWER_VALUES_LOGGED_OUT;
    }
}