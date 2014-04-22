package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.MenuCategoryAdapter;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenuCategoryListener implements RequestListener<List<String>> 
{
	private final Context context;
	
	public MenuCategoryListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onRequestSuccess(List<String> categories) 
	{
		ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
		MenuCategoryAdapter adapter = (MenuCategoryAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(categories);
	}

}
