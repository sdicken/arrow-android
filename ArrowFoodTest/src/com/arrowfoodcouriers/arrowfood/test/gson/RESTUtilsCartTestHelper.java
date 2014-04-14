package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.Response;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsCartTestHelper 
{
	private final RESTUtils utils;
	
	public RESTUtilsCartTestHelper(RESTUtils utils)
	{
		this.utils = utils;
	}
	
	public void testPlaceOrder()
	{
		ResponseEntity<String> responseEntity = utils.getMenus();
		Menu[] menus = RESTUtils.convertResponseEntityToModel(responseEntity, Menu[].class);
		String menuName = menus[0].getName();
		String restaurantName = menus[0].getRestaurant();
		MenuItem item1 = menus[0].getItems()[0];
		String itemName = item1.getName();
		Integer quantity = 2;
		responseEntity = utils.postCart(restaurantName, menuName, itemName, quantity);
		Response response = RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
		assertTrue(response.getSuccess());
		responseEntity = utils.getCart();
		Cart cart = RESTUtils.convertResponseEntityToModel(responseEntity, Cart.class);
		assertTrue(cart.getItems()[0].getMenuId().equals(menuName));
	}
}
