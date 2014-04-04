package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Coupon 
{
	private String token;
	private String type;
	private String param;
	private Date updated;
	private Date created;
	private Integer claims;
	
	public Coupon(String token, String type, String param, Date updated, Date created, Integer claims)
	{
		this.token = token;
		this.type = type;
		this.param = param;
		this.updated = updated;
		this.created = created;
		this.claims = claims;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getClaims() {
		return claims;
	}

	public void setClaims(Integer claims) {
		this.claims = claims;
	}
	
}
