package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.octo.android.robospice.request.SpiceRequest;

public class CartContentsRequest extends SpiceRequest<List<CartItem>> 
{
	@SuppressWarnings("unchecked")
	public CartContentsRequest()
	{
		super((Class<List<CartItem>>) new ArrayList<CartItem>().getClass());
	}

	@Override
	public List<CartItem> loadDataFromNetwork() throws Exception 
	{
		return RESTUtils.getCartItems();
	}
}
