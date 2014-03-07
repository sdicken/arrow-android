package com.arrowfoodcouriers.arrowfood.Interfaces;

import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartRegistration;
import com.arrowfoodcouriers.arrowfood.OpenCart.ThisitaCookieManager;

/**
 * Created by Sam on 2/22/14.
 */
public interface IOpenCartSession
{
    Boolean IsAuthenticated();
    Boolean Login(String username, String password);
    void AttachLoginDialogCallback(LoginDialogCallback loginDialogCallback);
    Boolean Register(OpenCartRegistration registration);
    ThisitaCookieManager GetCookieManager();
}
