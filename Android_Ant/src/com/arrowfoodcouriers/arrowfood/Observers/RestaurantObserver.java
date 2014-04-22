package com.arrowfoodcouriers.arrowfood.Observers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;

public class RestaurantObserver extends BroadcastReceiver {

	private Loader<?> loader;
	
	public RestaurantObserver(Loader<?> loader) {
		this.loader = loader;
		
		IntentFilter filter = new IntentFilter("com.arrowfoodcouriers.arrowfood.RESTAURANT_ITEM_CHANGED");
		this.loader.getContext().registerReceiver(this, filter);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		loader.onContentChanged();
		
	}
}
