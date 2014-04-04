package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class MenuItemOption 
{
	private String name;
	private String description;
	private String type;
	private String param;
	private Date created;
	
	public MenuItemOption(String name, String description, String type, String param, Date created)
	{
		this.name = name;
		this.description = description;
		this.type = type;
		this.param = param;
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
