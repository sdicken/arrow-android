package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.MenuItemOption;
import com.google.gson.Gson;

public class MenuDeserializerTest 
{
	private Gson gson;
	private String json;
	private Menu menu;
	
	private String name;
	private String restaurant;
	private byte[] image;
	private byte[] icon;
	private MenuItem[] items;
	private String[] tags;
	private Date updated;
	private Date created;
	private Integer orders;
	
	private MenuItemOption[] options;
	
	@Before
	public void setUp()
	{
		gson = new Gson();
		
		name = new String("thisita");
		restaurant = new String("Qdoba");
		image = null;
		icon = null;
		
		int size = 2;
		items = new MenuItem[size];
		
		options = new MenuItemOption[size];
		options[0] = new MenuItemOption("Rice", "Brown or white", "", "", new Date());
		options[1] = new MenuItemOption("Beans", "Black or pinto", "", "", new Date());
		
		items[0] = new MenuItem("Burrito deluxe", 0.99, null, null, null, options, "descr", new Date(), 11);
		items[1] = new MenuItem("Burrito supreme", 0.99, null, null, null, options, "descr", new Date(), 11);
		tags = new String[size];
		tags[0] = new String("Mexican");
		tags[1] = new String("Authentic");
		updated = new Date();
		created = new Date();
		orders = Integer.valueOf(10);
		
		menu = new Menu(name, restaurant, image, icon, items, tags, updated, created, orders);
		
		json = gson.toJson(menu);
	}
	
	@Test
	public void testMenuEquals()
	{
		Menu deserializedMenu = gson.fromJson(json, Menu.class);
		assertTrue(deserializedMenu.equals(menu));
	}
}
