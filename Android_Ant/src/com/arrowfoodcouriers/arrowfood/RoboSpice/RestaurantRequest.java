package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.octo.android.robospice.request.SpiceRequest;

public class RestaurantRequest extends SpiceRequest<List<Restaurant>> 
{
	@SuppressWarnings("unchecked")
	public RestaurantRequest() 
	{
		super((Class<List<Restaurant>>) new ArrayList<Restaurant>().getClass());
	}

	@Override
	public List<Restaurant> loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getRestaurants();
	}
}
