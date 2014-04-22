package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.octo.android.robospice.request.SpiceRequest;

public class MenuItemRequest extends SpiceRequest<MenuItem> 
{
	private final String restaurantName;
	private final String menuName;
	private final String itemName;
	public MenuItemRequest(String restaurantName, String menuName, String itemName) 
	{
		super(MenuItem.class);
		this.restaurantName = restaurantName;
		this.menuName = menuName;
		this.itemName = itemName;
	}

	@Override
	public MenuItem loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getMenuItem(restaurantName, menuName, itemName);
	}
}
