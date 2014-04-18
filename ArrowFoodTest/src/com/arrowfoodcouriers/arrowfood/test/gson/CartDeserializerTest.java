package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.Models.Coupon;
import com.google.gson.Gson;

public class CartDeserializerTest 
{
	private Gson gson;
	private String json;
	private Cart cart;
	
	private String username;
	private Date created;
	private Date updated;
	private CartItem[] items;
	private Coupon[] coupons;
	private Double total;
	private CartItemOption[] itemOptions;
	
	@Before
	public void setUp()
	{
		gson = new Gson();
		
		username = new String("thisita");
		created = new Date();
		updated = new Date();

		int size = 2;
		items = new CartItem[size];
		itemOptions = new CartItemOption[size];
		itemOptions[0] = new CartItemOption("Side", "Select", "Rice");
		itemOptions[1] = new CartItemOption("Side", "Select", "Beans");
		items[0] = new CartItem("Lunch", "Qdoba", "Burrito deluxe", itemOptions, 1, 2.99, new Date(), new Date());
		items[1] = new CartItem("Breakfast", "Quills", "Small coffee", itemOptions, 1, 2.99, new Date(), new Date());
		coupons = new Coupon[size];
		coupons[0] = new Coupon("token1", null, null, "", "", new Date(), new Date(), 11);
		coupons[1] = new Coupon("token2", null, null, "", "", new Date(), new Date(), 11);
		
		total = Double.valueOf(11.44);
		
		cart = new Cart(username, created, updated, items, coupons, total);
		
		json = gson.toJson(cart);
	}
	
	@Test
	public void testCartEquals()
	{
		Cart deserializedCart = new Gson().fromJson(json, Cart.class);
		assertTrue(deserializedCart.equals(cart));
	}
}
