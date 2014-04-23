package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartRequestListener implements RequestListener<Cart>
{
	private Context context;
	
	public CartRequestListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(Cart cart) 
	{
		ListView listView = (ListView) ((Activity)context).findViewById(android.R.id.list);
		CartAdapter adapter = (CartAdapter) listView.getAdapter();
		adapter.clear();
		adapter.addAll(FilterUtils.getCartItems(cart));		
	}

}
