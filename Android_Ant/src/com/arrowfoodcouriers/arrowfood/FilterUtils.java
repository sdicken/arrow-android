package com.arrowfoodcouriers.arrowfood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.Interfaces.IUpdateable;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;

public class FilterUtils 
{
	// filter through data in cart
	public static List<CartItem> getCartItems(Cart cart)
	{
		CartItem[] items = cart.getItems();
		return Arrays.asList(items);
	}
	
	public static <T extends IUpdateable> Date getOldestUpdated(T[] data)
	{
		Date oldest = data[0].getUpdated();
		for(int i = 1; i < data.length; i++)
		{
			if(data[i].getUpdated().before(oldest))
			{
				oldest = data[i].getUpdated();
			}
		}
		return oldest;
	}
	
	// filter through data in menu
	public static List<String> getMenuCategories(Menu[] menus, String restaurantName)
	{
		List<String> menuCategories = new ArrayList<String>();
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName))
			{
				menuCategories.add(menu.getName());
			}
		}
		return menuCategories;
	}
	
	// filter through data in menu
	public static List<MenuItem> getMenuItems(Menu[] menus, String restaurantName, String menuName)
	{
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName) && menu.getName().equals(menuName))
			{
				MenuItem[] items = menu.getItems();
				return Arrays.asList(items);
			}
		}
		return menuItems;
	}
	
	// filter through data in menu
	public static MenuItem getMenuItem(Menu[] menus, String restaurantName, String menuName, String itemName)
	{
		MenuItem menuItem = null;
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName) && menu.getName().equals(menuName))
			{
				MenuItem[] items = menu.getItems();
				for(int j = 0; j < items.length; j++)
				{
					MenuItem item = items[j];
					if(item.getName().equals(itemName))
					{
						return item;
					}
				}
			}
		}
		return menuItem;
	}
}
