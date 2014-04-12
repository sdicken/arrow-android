package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Email;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.google.gson.Gson;

public class RestaurantDeserializerTest 
{
	private Gson gson;
	private String json;
	private Restaurant restaurant;
	
	private String name;
	private byte[] image;
	private byte[] icon;
	private String description;
	private String[] tags;
	private Email[] emails;
	private Phone[] phones;
	private Address[] addresses;
	private Date created;
	private Date updated;
	private Integer orders;
	
	@Before
	public void setUp()
	{
		gson = new Gson();
		
		name = new String("Qdoba");
		image = null;
		icon = null;
		description = new String("Mexican Grill");
		
		int size = 2;
		tags = new String[size];
		tags[0] = new String("TexMex");
		tags[1] = new String("Authentic");
		
		emails = new Email[size];
		emails[0] = new Email("Bob", "bob@bob.bob", new Date());
		emails[1] = new Email("Bobby", "bobby@bob.bob", new Date());
		
		phones = new Phone[size];
		phones[0] = new Phone("Larry", "8675309", new Date());
		phones[1] = new Phone("Lawrence", "8675309", new Date());
		
		addresses = new Address[size];
		addresses[0] = new Address("311 Brook Street", "", "Louisville", "KY", "40204");
		addresses[1] = new Address("411 Brook Street", "", "Louisville", "KY", "40205");
		
		created = new Date();
		updated = new Date();
		orders = Integer.valueOf(7);
		
		restaurant = new Restaurant(name, image, icon, description, tags, emails, phones, addresses, created, updated, orders);
		
		json = gson.toJson(restaurant);
	}
	
	@Test
	public void testRestaurantEquals()
	{
		Restaurant deserializedRestaurant = gson.fromJson(json, Restaurant.class);
		assertTrue(deserializedRestaurant.equals(restaurant));
	}
}
