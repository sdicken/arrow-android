package com.arrowfoodcouriers.arrowfood.RoboSpice;

import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class LoginRequest extends SpringAndroidSpiceRequest<Response> 
{
	private String username;
	private String password;
	
	public LoginRequest(String username, String password) 
	{
		super(Response.class);
		this.username = username;
		this.password = password;
	}

	@Override
	public Response loadDataFromNetwork() throws Exception 
	{
		ResponseEntity<String> responseEntity = RESTUtils.login(username, password);
		return RESTUtils.convertResponseEntityToModel(responseEntity, Response.class);
	}

}
