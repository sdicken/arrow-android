package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.Response;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsCartTest 
{
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
	}
	
	@Test
	public void testPlaceOrder()
	{
		ResponseEntity<String> responseEntity = Utils.getMenus();
		Menu[] menus = Utils.convertResponseEntityToModel(responseEntity, Menu[].class);
		String menuName = menus[0].getName();
		String restaurantName = menus[0].getRestaurant();
		MenuItem item1 = menus[0].getItems()[0];
		String itemName = item1.getName();
		Integer quantity = 2;
		responseEntity = Utils.postCart(restaurantName, menuName, itemName, quantity);
		Response response = Utils.convertResponseEntityToModel(responseEntity, Response.class);
		assertTrue(response.getSuccess());
	}
}
