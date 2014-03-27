package com.arrowfoodcouriers.arrowfood.Models;

public class CartItem 
{
	private final String menuId;
	private final String itemId;
	private final Integer quantity;
	
	public CartItem(String menuId, String itemId, Integer quantity)
	{
		this.menuId = menuId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
}
