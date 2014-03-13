package com.arrowfoodcouriers.arrowfood.Interfaces;

import android.os.Parcel;
import android.os.Parcelable;

import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartItem;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartRegistration;
import com.arrowfoodcouriers.arrowfood.OpenCart.ThisitaCookieManager;

/**
 * Created by Sam on 2/22/14.
 */
// Extend Parcelable to use writeParcelable for interface objects
public interface ISession extends Parcelable
{
    Boolean IsAuthenticated();
    Boolean Login(String username, String password);
    Boolean Register(OpenCartRegistration registration);
    ThisitaCookieManager GetCookieManager();
    int describeContents();
    void writeToParcel(Parcel out, int flags);
	void Logout();
	void deauthenticate();
	void ParseEditHTML(String response);
	void authenticate();
	Boolean AddToCart(OpenCartItem openCartItem);
}
