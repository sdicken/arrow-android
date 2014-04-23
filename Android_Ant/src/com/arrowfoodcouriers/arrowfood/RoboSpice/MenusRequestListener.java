package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenusRequestListener implements RequestListener<Menu[]>
{
	Context context;
	
	public MenusRequestListener(Context context) {
		this.context = context;
	}

	@Override
	public void onRequestFailure(SpiceException e) 
	{
		Log.d("menu", "failed");
	}

	@Override
	public void onRequestSuccess(Menu[] menus) 
	{
		Intent intent = new Intent("com.arrowfoodcouriers.arrowfood.MENU_ITEM_CHANGED");
		GsonDataLoader<Menu[]> gsonLoader = new GsonDataLoader<Menu[]>(context, "menus", Menu[].class);
		gsonLoader.saveData(menus);
		context.sendBroadcast(intent);
	}
	
}
