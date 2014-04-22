package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.octo.android.robospice.request.SpiceRequest;

public class MenuCategoryRequest extends SpiceRequest<List<String>> 
{
	private final String restaurantName;
	@SuppressWarnings("unchecked")
	public MenuCategoryRequest(String restaurantName) 
	{
		super((Class<List<String>>) new ArrayList<String>().getClass());
		this.restaurantName = restaurantName;
	}

	@Override
	public List<String> loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getMenuCategories(restaurantName);
	}
}
