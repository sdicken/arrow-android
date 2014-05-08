package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.content.Context;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartDeleteItemQuantityRequestListener implements RequestListener<Response>
{
	private final Context context;
	
	public CartDeleteItemQuantityRequestListener(Context context)
	{
		this.context = context;
	}

	@Override
	public void onRequestFailure(SpiceException e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(Response response)
	{
		CartRequest request = new CartRequest();
		MainActivity.spiceManager.execute(request, new CartRequestListener(context));
	}

}
