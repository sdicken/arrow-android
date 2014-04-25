package com.arrowfoodcouriers.arrowfood;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class Utils 
{
	public static void loadFragment(FragmentManager fragmentManager, Fragment newFragment)
    {
		Fragment currentFragment = fragmentManager.findFragmentById(R.id.container);
		if(currentFragment.getClass() != newFragment.getClass())
		{
	    	FragmentTransaction ft = fragmentManager.beginTransaction();
	    	ft.replace(R.id.container, newFragment);
	    	ft.addToBackStack(null);
	    	ft.commit();
		}
    }
}
