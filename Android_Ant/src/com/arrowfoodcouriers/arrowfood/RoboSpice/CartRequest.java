package com.arrowfoodcouriers.arrowfood.RoboSpice;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.octo.android.robospice.request.SpiceRequest;

public class CartRequest extends SpiceRequest<Cart> 
{
	public CartRequest()
	{
		super(Cart.class);
	}

	@Override
	public Cart loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getCart();
	}
}
