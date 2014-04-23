package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenusUpdatedRequestListener<T> implements RequestListener<Response> 
{
	private final RequestListener<T> listener;
	
	public MenusUpdatedRequestListener(RequestListener<T> listener)
	{
		this.listener = listener;
	}

	@Override
	public void onRequestFailure(SpiceException e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestSuccess(Response response) 
	{
		SpiceRequest<T> request = (SpiceRequest<T>) new MenusRequestWithCacheLogic(response.getUpdated());
		MainActivity.spiceManager.execute(request, listener);
	}
}
