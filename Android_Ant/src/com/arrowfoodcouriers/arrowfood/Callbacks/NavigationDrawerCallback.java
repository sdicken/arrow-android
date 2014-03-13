package com.arrowfoodcouriers.arrowfood.Callbacks;

import roboguice.inject.InjectView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;

public class NavigationDrawerCallback implements INavigationDrawerCallback 
{
	@InjectView(R.id.left_drawer) ListView mDrawerList;
	
	public NavigationDrawerCallback()
	{
		
	}
	
    public void onNavigationDrawerUpdated() 
    {
        ((BaseAdapter)mDrawerList.getAdapter()).notifyDataSetChanged();
    }
}
