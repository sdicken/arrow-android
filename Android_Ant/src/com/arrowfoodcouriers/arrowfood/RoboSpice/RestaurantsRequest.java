package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.octo.android.robospice.request.SpiceRequest;

public class RestaurantsRequest extends SpiceRequest<Restaurant[]> 
{
	public RestaurantsRequest() 
	{
		super(Restaurant[].class);
	}

	@Override
	public Restaurant[] loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getRestaurants();
	}
	
	public String createCacheKey()
	{
		return "restaurants";
	}
}
