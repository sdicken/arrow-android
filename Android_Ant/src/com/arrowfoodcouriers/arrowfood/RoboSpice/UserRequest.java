package com.arrowfoodcouriers.arrowfood.RoboSpice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.octo.android.robospice.request.SpiceRequest;

public class UserRequest extends SpiceRequest<User> 
{

	public UserRequest() {
		super(User.class);
	}

	@Override
	public User loadDataFromNetwork() throws Exception {
		ResponseEntity<String> responseEntity = null;
		try
		{
			responseEntity = RESTUtils.getUser();
		}
		catch(Exception e)
		{
			HttpStatus status = ((HttpClientErrorException) e).getStatusCode();
			if(status.equals(HttpStatus.BAD_REQUEST))
			{
				return null;
			}
		}
		return RESTUtils.convertResponseEntityToModel(responseEntity, User.class);
	}
}
