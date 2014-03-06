package com.arrowfoodcouriers.arrowfood;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginClass;

/**
 * Created by Ryan on 2/17/14.
 */
public class LoginClass implements ILoginClass {

    private boolean is_logged_in;

    public LoginClass() {
        is_logged_in = false;
    }

    public void LoginUser(String userName, String password) {
        is_logged_in = true;
    }

    public void LogoutUser() {
        is_logged_in = false;
    }

    public boolean IsUserLoggedIn() {
        return is_logged_in;
    }
}
