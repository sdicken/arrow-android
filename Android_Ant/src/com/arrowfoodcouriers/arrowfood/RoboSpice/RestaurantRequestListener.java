package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class RestaurantRequestListener implements RequestListener<List<Restaurant>>
{
	private Context context;
	
	public RestaurantRequestListener(Context context)
	{
		this.context = context;
	}

	@Override
	public void onRequestFailure(SpiceException e) 
	{
		Log.d("spice","fail");			
	}

	@Override
	public void onRequestSuccess(List<Restaurant> restaurants) 
	{
		Log.d("spice","succeed");
		GsonDataLoader<Restaurant[]> gsonLoader = new GsonDataLoader<Restaurant[]>(context, "restaurants", Restaurant[].class);
		gsonLoader.saveData((Restaurant[])restaurants.toArray());
	}
	
}