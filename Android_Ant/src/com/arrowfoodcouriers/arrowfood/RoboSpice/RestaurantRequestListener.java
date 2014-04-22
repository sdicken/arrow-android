package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
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
		ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
		RestaurantListAdapter adapter = (RestaurantListAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(restaurants);
	}
	
}