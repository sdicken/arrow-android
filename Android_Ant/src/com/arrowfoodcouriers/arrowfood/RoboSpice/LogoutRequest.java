package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.octo.android.robospice.request.SpiceRequest;

public class LogoutRequest extends SpiceRequest<Void> 
{

	public LogoutRequest() 
	{
		super(Void.class);
	}

	@Override
	public Void loadDataFromNetwork() throws Exception 
	{
		RESTUtils.postLogout();
		return null;
	}

}
