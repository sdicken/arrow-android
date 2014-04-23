package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuCategoryAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenuCategoryRequestListener implements RequestListener<Menu[]> 
{
	private final Context context;
	private final String restaurantName;
	
	public MenuCategoryRequestListener(Context context, String restaurantName)
	{
		this.context = context;
		this.restaurantName = restaurantName;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onRequestSuccess(Menu[] menus) 
	{
		ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
		MenuCategoryAdapter adapter = (MenuCategoryAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(FilterUtils.getMenuCategories(menus, restaurantName));
	}

}
