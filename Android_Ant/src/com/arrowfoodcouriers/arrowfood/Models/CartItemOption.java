package com.arrowfoodcouriers.arrowfood.Models;

public class CartItemOption 
{
	private String name;
	private String type;
	private String param;
	
	public CartItemOption(String name, String type, String param)
	{
		this.name = name;
		this.type = type;
		this.param = param;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	
}
