package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Achievement 
{
	private String name;
	private String type;
	private String param;
	private Date created;
	private Date updated;
	
	public Achievement(String name, String type, String param, Date created, Date updated)
	{
		this.name = name;
		this.type = type;
		this.param = param;
		this.created = created;
		this.updated = updated;
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
