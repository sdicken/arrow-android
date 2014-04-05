package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.Coupon;
import com.google.gson.Gson;

public class CartDeserializerTest 
{
	private Gson gson;
	private String json;
	private Cart cart;
	
	private String username;
	private Long created;
	private Long updated;
	private CartItem[] items;
	private Coupon[] coupons;
	private Double total;
	
	@Before
	public void setUp()
	{
		gson = new Gson();
		
		username = new String("thisita");
		created = new Date().getTime();
		updated = new Date().getTime();

		int size = 2;
		items = new CartItem[size];
		items[0] = new CartItem("Qdoba", "Burrito deluxe", 1, 2.99, new Date().getTime(), new Date().getTime());
		items[1] = new CartItem("Quills", "Small coffee", 1, 2.99, new Date().getTime(), new Date().getTime());
		coupons = new Coupon[size];
		coupons[0] = new Coupon("token1", null, null, "", "", new Date().getTime(), new Date().getTime(), 11);
		coupons[1] = new Coupon("token2", null, null, "", "", new Date().getTime(), new Date().getTime(), 11);
		
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
