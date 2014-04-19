package com.arrowfoodcouriers.arrowfood.RoboSpice;

import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.SpiceRequest;

public class CartAddRequest extends SpiceRequest<Response> 
{
	private final String restaurantName;
	private final String menuName;
	private final String itemName;
	private final Integer quantity;
	private final CartItemOption[] itemOptions;
	
	public CartAddRequest(String restaurantName, String menuName, String itemName, Integer quantity, CartItemOption[] itemOptions) 
	{
		super(Response.class);
		
		this.restaurantName = restaurantName;
		this.menuName = menuName;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemOptions = itemOptions;		
	}

	@Override
	public Response loadDataFromNetwork() throws Exception 
	{
		ResponseEntity<String> responseEntity = RESTUtils.postCart(restaurantName, menuName, itemName, quantity, itemOptions);
		return RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
	}

}
