package com.arrowfoodcouriers.arrowfood.Models;


public class CartItem 
{
	private String menuId;
	private String itemId;
	private Integer quantity;
	private Double total;
	private Long created;
	private Long updated;
	
	public CartItem(String menuId, 
			String itemId, 
			Integer quantity, 
			Double total,
			Long created,
			Long updated)
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

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}
}
