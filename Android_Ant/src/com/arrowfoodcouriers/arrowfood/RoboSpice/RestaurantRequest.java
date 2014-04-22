package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;

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
		ResponseEntity<String> responseEntity = RESTUtils.getRestaurants();
		Restaurant[] restaurantArray = RESTUtils.convertResponseEntityToModel(responseEntity, Restaurant[].class);
		return Arrays.asList(restaurantArray);
	}
}
