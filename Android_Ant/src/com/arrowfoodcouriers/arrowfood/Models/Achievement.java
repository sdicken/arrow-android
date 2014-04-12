package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;


public class Achievement 
{
	private String name;
	private byte[] image;
	private byte[] icon;
	private String type;
	private String param;
	private Date created;
	private Date updated;
	
	public Achievement(String name, 
			byte[] image,
			byte[] icon,
			String type, 
			String param, 
			Date created, 
			Date updated)
	{
		this.name = name;
		this.image = image;
		this.icon = icon;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
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
