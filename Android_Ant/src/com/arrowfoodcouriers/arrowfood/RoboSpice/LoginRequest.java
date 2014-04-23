package com.arrowfoodcouriers.arrowfood.RoboSpice;

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
		return RESTUtils.postLogin(username, password);
	}

}
