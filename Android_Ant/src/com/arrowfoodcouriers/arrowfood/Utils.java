package com.arrowfoodcouriers.arrowfood;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class Utils 
{
	public static void loadFragment(FragmentManager fragmentManager, Fragment fragment)
    {
    	FragmentTransaction ft = fragmentManager.beginTransaction();
    	ft.replace(R.id.container, fragment);
    	ft.addToBackStack(null);
    	ft.commit();
    }
}
