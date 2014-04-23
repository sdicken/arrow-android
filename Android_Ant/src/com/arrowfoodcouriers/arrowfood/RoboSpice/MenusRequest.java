package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.octo.android.robospice.request.SpiceRequest;

public class MenusRequest extends SpiceRequest<Menu[]> 
{
	public MenusRequest() 
	{
		super(Menu[].class);
	}

	@Override
	public Menu[] loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getMenus();
	}
	
	public String createCacheKey()
	{
		return "menus";
	}

}
