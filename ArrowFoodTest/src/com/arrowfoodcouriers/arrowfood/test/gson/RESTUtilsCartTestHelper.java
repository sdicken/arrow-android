package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.Order;
import com.arrowfoodcouriers.arrowfood.Models.Response;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsCartTestHelper 
{
	private String menuName;
	private String restaurantName;
	private String itemName;
	private MenuItem item1;
	private ResponseEntity<String> responseEntity;
	private Response response;
	
	private void testAddToCartSuccess()
	{
		responseEntity = RESTUtils.getMenus();
		Menu[] menus = RESTUtils.convertResponseEntityToModel(responseEntity, Menu[].class);
		menuName = menus[0].getName();
		restaurantName = menus[0].getRestaurant();
		item1 = menus[0].getItems()[0];
		itemName = item1.getName();
		Integer quantity = 2;
		CartItemOption[] itemOptions = new CartItemOption[1];
		itemOptions[0] = new CartItemOption(item1.getItemOptions()[0].getName(), 
				item1.getItemOptions()[0].getType(),
				item1.getItemOptions()[0].getParam());
		responseEntity = RESTUtils.postCart(restaurantName, menuName, itemName, quantity, itemOptions);
		response = RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
		assertTrue(response.getSuccess());
		responseEntity = RESTUtils.getCart();
		Cart cart = RESTUtils.convertResponseEntityToModel(responseEntity, Cart.class);
		assertTrue(cart.getItems()[0].getRestaurant().equals(restaurantName));
		assertTrue(cart.getItems()[0].getMenu().equals(menuName));
		assertTrue(cart.getItems()[0].getItem().equals(itemName));
	}
	
	public void testPlaceOrderSuccess()
	{
		testAddToCartSuccess();

		Address billing = new Address("Test Street", "", "Louisville", "KY", "40202");
		Address shipping = new Address("Test Avenue", "", "Louisville", "KY", "40205");
		Order order = new Order(billing, shipping);
		responseEntity = RESTUtils.postCartOrder(order);
		response = RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
		assertTrue(response.getSuccess());
	}
}
