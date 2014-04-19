package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartContentsListener implements RequestListener<List<CartItem>>
{
	private Context context;
	
	public CartContentsListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(List<CartItem> cartItems) 
	{
		ListView listView = (ListView) ((Activity)context).findViewById(android.R.id.list);
		CartAdapter adapter = (CartAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(cartItems);		
	}

}
