package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;
import java.util.Date;

import com.arrowfoodcouriers.arrowfood.Interfaces.IUpdateable;


public class Restaurant implements IUpdateable
{
	private String name;
	private byte[] image;
	private byte[] icon;
	private String description;
	private String[] tags;
	private Email[] emails;
	private Phone[] phones;
	private Address[] addresses;
	private Date created;
	private Date updated;
	private Integer orders;
	
	public Restaurant(String name,
			byte[] image,
			byte[] icon,
			String description,
			String[] tags,
			Email[] emails,
			Phone[] phones,
			Address[] addresses,
			Date created,
			Date updated,
			Integer orders)
	{
		this.name = name;
		this.image = image;
		this.icon = icon;
		this.description = description;
		this.tags = tags;
		this.emails = emails;
		this.phones = phones;
		this.addresses = addresses;
		this.created = created;
		this.updated = updated;
		this.orders = orders;
	}
	
	public Restaurant(String name, String description)
	{
		this(name, null, null, description, null, null, null, null, null, null, null);
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Email[] getEmails() {
		return emails;
	}

	public void setEmails(Email[] emails) {
		this.emails = emails;
	}

	public Phone[] getPhones() {
		return phones;
	}

	public void setPhones(Phone[] phones) {
		this.phones = phones;
	}

	public Address[] getAddresses() {
		return addresses;
	}

	public void setAddresses(Address[] addresses) {
		this.addresses = addresses;
	}

	

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(addresses);
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(emails);
		result = prime * result + Arrays.hashCode(icon);
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + Arrays.hashCode(phones);
		result = prime * result + Arrays.hashCode(tags);
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
		if (!(obj instanceof Restaurant)) {
			return false;
		}
		Restaurant other = (Restaurant) obj;
		if (!Arrays.equals(addresses, other.addresses)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (!Arrays.equals(emails, other.emails)) {
			return false;
		}
		if (!Arrays.equals(icon, other.icon)) {
			return false;
		}
		if (!Arrays.equals(image, other.image)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (orders == null) {
			if (other.orders != null) {
				return false;
			}
		} else if (!orders.equals(other.orders)) {
			return false;
		}
		if (!Arrays.equals(phones, other.phones)) {
			return false;
		}
		if (!Arrays.equals(tags, other.tags)) {
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
