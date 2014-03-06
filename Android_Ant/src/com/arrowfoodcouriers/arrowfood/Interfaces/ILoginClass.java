package com.arrowfoodcouriers.arrowfood.Interfaces;

/**
 * Created by Ryan on 2/17/14.
 */
public interface ILoginClass {

    public boolean IsUserLoggedIn();

    public void LoginUser(String userName, String password);

    public void LogoutUser();

}
