package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.SpiceRequest;

public class CartDeleteItemQuantityRequest extends SpiceRequest<Response>
{
	private final String restaurantName;
	private final String menuName;
	private final String itemName;
	private final Integer quantity;
	
	public CartDeleteItemQuantityRequest(String restaurantName, String menuName, String itemName, Integer quantity)
	{
		super(Response.class);
		this.restaurantName = restaurantName;
		this.menuName = menuName;
		this.itemName = itemName;
		this.quantity = quantity;
	}

	@Override
	public Response loadDataFromNetwork() throws Exception
	{
		return RESTUtils.deleteCartItemQuantity(restaurantName, menuName, itemName, quantity);
	}

}
