package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.Date;
import java.util.concurrent.Future;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;

public class RestaurantsRequestWithCacheLogic extends RestaurantsRequest 
{
	private final Date updated;
	public RestaurantsRequestWithCacheLogic(Date updated)
	{
		super();
		this.updated = updated;
	}

	@Override
	public Restaurant[] loadDataFromNetwork() throws Exception 
	{
		if(updated == null)
		{
			return RESTUtils.getRestaurants();
		}
		else
		{
			Future<Restaurant[]> future = MainActivity.spiceManager.getDataFromCache(Restaurant[].class, createCacheKey());
			Restaurant[] restaurants = future.get();
			Date oldestUpdatedInCache = FilterUtils.getOldestUpdated(restaurants);
			if(updated.after(oldestUpdatedInCache))
			{
				return RESTUtils.getRestaurants();
			}
			else
			{
				return restaurants;
			}
		}
	}
}
