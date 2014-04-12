package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;
import java.util.Date;


public class Coupon 
{
	private String token;
	private byte[] image;
	private byte[] icon;
	private String type;
	private String param;
	private Date updated;
	private Date created;
	private Integer claims;
	
	public Coupon(String token, 
			byte[] image,
			byte[] icon,
			String type, 
			String param, 
			Date updated, 
			Date created, 
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claims == null) ? 0 : claims.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + Arrays.hashCode(icon);
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((param == null) ? 0 : param.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Coupon)) {
			return false;
		}
		Coupon other = (Coupon) obj;
		if (claims == null) {
			if (other.claims != null) {
				return false;
			}
		} else if (!claims.equals(other.claims)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (!Arrays.equals(icon, other.icon)) {
			return false;
		}
		if (!Arrays.equals(image, other.image)) {
			return false;
		}
		if (param == null) {
			if (other.param != null) {
				return false;
			}
		} else if (!param.equals(other.param)) {
			return false;
		}
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (updated == null) {
			if (other.updated != null) {
				return false;
			}
		} else if (!updated.equals(other.updated)) {
			return false;
		}
		return true;
	}
	
}
