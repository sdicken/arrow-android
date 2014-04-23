package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.SpiceRequest;

public class MenusUpdatedRequest extends SpiceRequest<Response> 
{
	public MenusUpdatedRequest() 
	{
		super(Response.class);
	}

	@Override
	public Response loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getMenusUpdated();
	}
}
