package com.arrowfoodcouriers.arrowfood.Observers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;

public class LoginObserver extends BroadcastReceiver {

	private Loader<?> loader;
	
	private boolean loggedOn;
	
	public LoginObserver(Loader<?> loader) {
		this.loader = loader;
		this.loggedOn = false;
	
		IntentFilter filter = new IntentFilter("com.arrowfoodcouriers.arrowfood.ACTION_LOGIN");
		this.loader.getContext().registerReceiver(this, filter);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		loggedOn = intent.getBooleanExtra("logged_on", false);
		loader.onContentChanged();
	}

	public boolean isLoggedOn() {
		return loggedOn;
	}

}
