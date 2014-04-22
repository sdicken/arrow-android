package com.arrowfoodcouriers.arrowfood.Loaders;

import java.util.Arrays;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Observers.MenuObserver;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class MenuLoader extends AsyncTaskLoader<List<Menu>> {

	private final GsonDataLoader<Menu[]> dataLoader;
	private List<Menu> data;
	
	public MenuLoader(Context context) {
		super(context);
		
		dataLoader = new GsonDataLoader<Menu[]>(context, "menus", Menu[].class);
	}

	@Override
	public List<Menu> loadInBackground() {
		
		List<Menu> data = Arrays.asList(dataLoader.loadData());
		
		return data;
	}
	
	@Override
	public void deliverResult(List<Menu> data) {
		if (isReset()) {
			releaseResources(data);
			return;
		}
		
		List<Menu> oldData = this.data;
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
			observer = new MenuObserver(this);
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
	public void onCanceled(List<Menu> data) {
		super.onCanceled(data);
		
		releaseResources(data);
	}

	private void releaseResources(List<Menu> data) {

	}

	private MenuObserver observer;
}
