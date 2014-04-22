package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.octo.android.robospice.request.SpiceRequest;

public class MenuItemsRequest extends SpiceRequest<List<MenuItem>> 
{
	private final String restaurantName;
	private final String menuName;
	
	@SuppressWarnings("unchecked")
	public MenuItemsRequest(String restaurantName, String menuName) 
	{
		super((Class<List<MenuItem>>) new ArrayList<MenuItem>().getClass());
		this.restaurantName = restaurantName;
		this.menuName = menuName;
	}

	@Override
	public List<MenuItem> loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getMenuItems(restaurantName, menuName);
	}

}
