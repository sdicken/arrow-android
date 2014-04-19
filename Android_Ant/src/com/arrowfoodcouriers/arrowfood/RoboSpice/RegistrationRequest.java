package com.arrowfoodcouriers.arrowfood.RoboSpice;

import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.octo.android.robospice.request.SpiceRequest;

public class RegistrationRequest extends SpiceRequest<Response>
{
	private final String username;
	private final String password;
	private final String email;
	private final String name;
	private final Address address;
	private final Phone phone;
	
	public RegistrationRequest(String username, String password, String email, 
			String name, Address address, Phone phone) 
	{
		super(Response.class);
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public Response loadDataFromNetwork() throws Exception 
	{
		Phone[] phones = new Phone[1];
		phones[0] = phone;
		Address[] addresses = new Address[1];
		addresses[0] = address;
		User user = new User(username, password, email, name, addresses, phones);
		ResponseEntity<String> responseEntity = RESTUtils.postUser(user);
		return RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
	}

}
