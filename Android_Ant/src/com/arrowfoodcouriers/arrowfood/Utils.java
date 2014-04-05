package com.arrowfoodcouriers.arrowfood;

import java.util.Date;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;

public class Utils 
{
	public static void loadFragment(FragmentManager fragmentManager, Fragment fragment)
    {
    	FragmentTransaction ft = fragmentManager.beginTransaction();
    	ft.replace(R.id.container, fragment);
    	ft.addToBackStack(null);
    	ft.commit();
    }

	public static CartItem[] getCartItems() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
		int size = 2;
		CartItem [] items = new CartItem[size];
		items[0] = new CartItem("Qdoba", "Burrito deluxe", 1, 2.99, new Date().getTime(), new Date().getTime());
		items[1] = new CartItem("Quills", "Small coffee", 1, 2.99, new Date().getTime(), new Date().getTime());
		return items;
	}

	public static Cart getCart() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
		return new Cart("", new Date().getTime(), new Date().getTime(), null, null, 4.22);
	}
}
