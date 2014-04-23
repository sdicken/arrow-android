package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenuItemsRequestListener implements RequestListener<Menu[]>
{
	private final Context context; 
	private final String restaurantName;
	private final String menuName;
	
	public MenuItemsRequestListener(Context context, String restaurantName, String menuName)
	{
		this.context = context;
		this.restaurantName = restaurantName;
		this.menuName = menuName;
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
		MenuAdapter adapter = (MenuAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(FilterUtils.getMenuItems(menus, restaurantName, menuName));
	}
	
}
