package com.arrowfoodcouriers.arrowfood.Models;

public class CartItem 
{
	private String menuId;
	private String itemId;
	private Integer quantity;
	
	public CartItem(String menuId, String itemId, Integer quantity)
	{
		this.menuId = menuId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
