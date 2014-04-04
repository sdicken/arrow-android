package com.arrowfoodcouriers.arrowfood.Models;


public class Coupon 
{
	private String token;
	private byte[] image;
	private byte[] icon;
	private String type;
	private String param;
	private Long updated;
	private Long created;
	private Integer claims;
	
	public Coupon(String token, 
			byte[] image,
			byte[] icon,
			String type, 
			String param, 
			Long updated, 
			Long created, 
			Integer claims)
	{
		this.token = token;
		this.image = image;
		this.icon = icon;
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

	public Integer getClaims() {
		return claims;
	}

	public void setClaims(Integer claims) {
		this.claims = claims;
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

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
	
}
