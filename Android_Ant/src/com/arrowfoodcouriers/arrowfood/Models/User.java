package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;


public class User 
{
	private String username;
	private String password;
	private String role;
	private String email;
	private String name;
	private byte[] image;
	private byte[] icon;
	private String[] achievements;
	private Phone[] phones;
	private Address[] addresses;
	private Long created;
	private Long updated;
	private Integer orders;
	private Integer deliveries;
	
	public User(String username, String password, String role, String email, String name,
			byte[] image, byte[] icon, String[] achievements, Phone[] phones,
			Address[] addresses, Long created, Long updated, Integer orders, 
			Integer deliveries) 
	{
		
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.name = name;
		this.image = image;
		this.icon = icon;
		this.achievements = achievements;
		this.phones = phones;
		this.addresses = addresses;
		this.created = created;
		this.updated = updated;
		this.orders = orders;
		this.deliveries = deliveries;
	}

	/**
	 * Minimum required parameters to register a new User
	 * @param username
	 * @param password
	 * @param email
	 * @param name
	 */
	public User(String username, String password, String email, String name)
	{
		this(username, password, null, email, name, null, null, null, null, null, null, null, null, null);
	}
	
	/**
	 * Expanded set of parameters for registering a new User
	 * @param username
	 * @param password
	 * @param email
	 * @param name
	 * @param address
	 * @param phone
	 */
	public User(String username, String password, String email, String name, Address[] addresses, Phone[] phones)
	{
		this(username, password, null, email, name, null, null, null, phones, addresses, null, null, null, null);
	}
	
	public User(String username, String password)
	{
		this(username, password, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String[] getAchievements() {
		return achievements;
	}

	public void setAchievements(String[] achievements) {
		this.achievements = achievements;
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

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Integer deliveries) {
		this.deliveries = deliveries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(achievements);
		result = prime * result + Arrays.hashCode(addresses);
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((deliveries == null) ? 0 : deliveries.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + Arrays.hashCode(icon);
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(phones);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (!Arrays.equals(achievements, other.achievements)) {
			return false;
		}
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
		if (deliveries == null) {
			if (other.deliveries != null) {
				return false;
			}
		} else if (!deliveries.equals(other.deliveries)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
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
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (!Arrays.equals(phones, other.phones)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (updated == null) {
			if (other.updated != null) {
				return false;
			}
		} else if (!updated.equals(other.updated)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
	
	
}
