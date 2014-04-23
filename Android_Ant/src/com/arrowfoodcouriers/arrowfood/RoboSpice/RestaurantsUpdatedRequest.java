package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.SpiceRequest;

public class RestaurantsUpdatedRequest extends SpiceRequest<Response> 
{
	public RestaurantsUpdatedRequest() 
	{
		super(Response.class);
	}

	@Override
	public Response loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getRestaurantsUpdated();
	}
}
