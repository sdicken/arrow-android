package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class RestaurantsRequestListener implements RequestListener<Restaurant[]>
{
	private Context context;
	
	public RestaurantsRequestListener(Context context)
	{
		this.context = context;
	}

	@Override
	public void onRequestFailure(SpiceException e) 
	{		
	}

	@Override
	public void onRequestSuccess(Restaurant[] restaurants) 
	{
		ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
		RestaurantListAdapter adapter = (RestaurantListAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(Arrays.asList(restaurants));
	}
	
}