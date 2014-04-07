package com.arrowfoodcouriers.arrowfood.Loaders;

import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.Observers.LoginObserver;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class UserAccountLoader extends AsyncTaskLoader<User> {

	private final GsonDataLoader<User> dataLoader;
	private User data;
	
	public UserAccountLoader(Context context) {
		super(context);
		
		dataLoader = new GsonDataLoader<User>(context, "user", User.class);
	}

	@Override
	public User loadInBackground() {
		
		// Load the user data from internal storage
		User data = dataLoader.loadData();
		
		return data;
	}
	
	@Override
	public void deliverResult(User data) {
		if (isReset()) {
			releaseResources(data);
			return;
		}
		
		User oldData = this.data;
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
	public void onCanceled(User data) {
		super.onCanceled(data);
		
		releaseResources(data);
	}

	private void releaseResources(User data) {
		// TODO Auto-generated method stub
		
	}

	private LoginObserver observer;
}
