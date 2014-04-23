package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.content.Context;
import android.content.Intent;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class LogoutRequestListener implements RequestListener<Void> 
{
	private final Context context;
	
	public LogoutRequestListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{		
	}

	@Override
	public void onRequestSuccess(Void v) 
	{
		Intent intent = new Intent();
		intent.setAction("com.arrowfoodcouriers.arrowfood.ACTION_LOGIN");
		context.sendBroadcast(intent);
	}

}
