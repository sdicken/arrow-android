package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.SpiceRequest;

public class CartDeleteItemRequest extends SpiceRequest<Response> 
{
	private final String restaurantName;
	private final String menuName;
	private final String itemName;
	
	public CartDeleteItemRequest(String restaurantName, String menuName, String itemName) 
	{
		super(Response.class);
		this.restaurantName = restaurantName;
		this.menuName = menuName;
		this.itemName = itemName;
	}

	@Override
	public Response loadDataFromNetwork() throws Exception
	{
		return RESTUtils.deleteCartItem(restaurantName, menuName, itemName);
	}

}
