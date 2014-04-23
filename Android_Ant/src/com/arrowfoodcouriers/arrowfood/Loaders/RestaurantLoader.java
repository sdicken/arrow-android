package com.arrowfoodcouriers.arrowfood.Loaders;

import java.util.Arrays;
import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.Observers.RestaurantObserver;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

public class RestaurantLoader extends AsyncTaskLoader<List<Restaurant>> {

	private final GsonDataLoader<Restaurant[]> dataLoader;
	private List<Restaurant> data;
	
	public RestaurantLoader(Context context) {
		super(context);
		
		dataLoader = new GsonDataLoader<Restaurant[]>(context, "restaurants", Restaurant[].class);
	}

	@Override
	public List<Restaurant> loadInBackground() {
		
		try {
		List<Restaurant> data = Arrays.asList(dataLoader.loadData());
		
		return data;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public void deliverResult(List<Restaurant> data) {
		if (isReset()) {
			releaseResources(data);
			return;
		}
		
		List<Restaurant> oldData = this.data;
		this.data = data;
		
		if (isStarted()) {
			super.deliverResult(data);
		}
		
		if (oldData != null && oldData != data) {
			releaseResources(oldData);
		}
	}
	
	@Override
	protected void onStartLoading() {
		if (data != null) {
			deliverResult(data);
		}
		
		if (observer == null) {
			observer = new RestaurantObserver(this);
		}
		
		if (takeContentChanged() || data == null) {
			forceLoad();
		}
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
	}
	
	@Override
	protected void onReset() {
		onStopLoading();
		
		if (data != null) {
			releaseResources(data);
			data = null;
		}
		
		if (observer != null) {
			getContext().unregisterReceiver(observer);
			observer = null;
		}
	}
	
	@Override
	public void onCanceled(List<Restaurant> data) {
		super.onCanceled(data);
		
		releaseResources(data);
	}

	private void releaseResources(List<Restaurant> data) {

	}

	private RestaurantObserver observer;
}
