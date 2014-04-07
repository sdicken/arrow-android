package com.arrowfoodcouriers.arrowfood.Loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.DrawerListObject;
import com.arrowfoodcouriers.arrowfood.Observers.LoginObserver;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class DrawerValuesLoader extends AsyncTaskLoader<List<DrawerListObject>> {
	private final GsonDataLoader<DrawerListObject[]> dataLoader;
	private List<DrawerListObject> data;
	
	public DrawerValuesLoader(Context context) {
		super(context);
		
		dataLoader = new GsonDataLoader<DrawerListObject[]>(context, "json/drawer_values", DrawerListObject[].class);
	}

	@Override
	public List<DrawerListObject> loadInBackground() {
		
		// Load the user data from internal storage
		List<DrawerListObject> data = Arrays.asList(dataLoader.loadAssetData());
		List<DrawerListObject> filteredData = new ArrayList<DrawerListObject>();
		
		if (observer != null) {
			// Filter data based on if logged in or not
			if (observer.isLoggedOn()) {
				for(DrawerListObject obj : data) {
					if (!obj.isLogoutOnly()) {
						filteredData.add(obj);
					}
				}
			}
			else {
				for(DrawerListObject obj : data) {
					if (!obj.isLoginOnly()) {
						filteredData.add(obj);
					}
				}
			}
		}
		else
		{
			for(DrawerListObject obj : data) {
				if (!obj.isLoginOnly()) {
					filteredData.add(obj);
				}
			}
		}
		
		return filteredData;
	}
	
	@Override
	public void deliverResult(List<DrawerListObject> data) {
		if (isReset()) {
			releaseResources(data);
			return;
		}
		
		List<DrawerListObject> oldData = this.data;
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
			observer = new LoginObserver(this);
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
	public void onCanceled(List<DrawerListObject> data) {
		super.onCanceled(data);
		
		releaseResources(data);
	}

	private void releaseResources(List<DrawerListObject> data) {
		// TODO Auto-generated method stub
		
	}

	private LoginObserver observer;
}
