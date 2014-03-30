package com.arrowfoodcouriers.arrowfood;

import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;

/**
 * Created by Ryan on 2/6/14.
 */
public class DrawerValues {
    private static final Object[] DRAWER_VALUES_LOGGED_IN = {
            new DrawerListObject("Home", 0, R.drawable.ic_empty),
            "ACCOUNT",
            new DrawerListObject("Profile", 3, R.drawable.ic_action_user),
            new DrawerListObject("Previous Orders", 5, R.drawable.ic_empty),
            new DrawerListObject("Favorites", 6, R.drawable.ic_action_star),
            "ORDERING",
            new DrawerListObject("Restaurants", 1, R.drawable.ic_empty),
            new DrawerListObject("Search", 2, R.drawable.ic_action_search),
            new DrawerListObject("Track", 3, R.drawable.ic_empty),
            "MORE",
            new DrawerListObject("Areas Served", 7, R.drawable.ic_empty),
            new DrawerListObject("About Us", 8, R.drawable.ic_action_about),
            new DrawerListObject("Sign Out", 9, R.drawable.ic_signout)
    };

    private static final Object[] DRAWER_VALUES_LOGGED_OUT = {
            new DrawerListObject("Home", 0, R.drawable.ic_empty),
            "ACCOUNT",
            new DrawerListObject("Login", 4, R.drawable.ic_account),
            "ORDERING",
            new DrawerListObject("Restaurants", 1, R.drawable.ic_empty),
            new DrawerListObject("Search", 2, R.drawable.ic_action_search),
            new DrawerListObject("Track", 3, R.drawable.ic_empty),
            "MORE",
            new DrawerListObject("Areas Served", 7, R.drawable.ic_empty),
            new DrawerListObject("About Us", 8, R.drawable.ic_action_about)
    };

    private ISession _session;

    public DrawerValues(ISession session)
    {
        this._session = session;
    }

    public Object[] getDrawerValues() {
        return (_session.IsAuthenticated()) ? DRAWER_VALUES_LOGGED_IN : DRAWER_VALUES_LOGGED_OUT;
    }
}