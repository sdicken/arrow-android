package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class CartItem 
{
	private String menuId;
	private String itemId;
	private Integer quantity;
	private Double total;
	private Date created;
	private Date updated;
	
	public CartItem(String menuId, 
			String itemId, 
			Integer quantity, 
			Double total,
			Date created,
			Date updated)
	{
		this.menuId = menuId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.total = total;
		this.created = created;
		this.updated = updated;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
