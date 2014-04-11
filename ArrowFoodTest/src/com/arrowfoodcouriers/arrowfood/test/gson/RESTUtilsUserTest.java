package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.Models.User;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsUserTest 
{
	private String username;
	private String password;
	private String role;
	private String email;
	private String name;
	private byte [] image;
	private byte[] icon;
	private String[] achievements;
	private Phone[] phones;
	private Address[] addresses;
	private Long created;
	private Long updated;
	private Integer orders;
	private Integer deliveries;

	private HttpStatus httpStatus;
	private User user;
	
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
		
		username = new String("bob");
		password = new String("pass");
		role = new String("Customer");
		email = new String("bob@bob.bob");
		name = new String("Bobby");
		image = null;
		icon = null;
		int size = 2;
		achievements = new String[size];
		phones = new Phone[size];
		addresses = new Address[size];
		created = new Date().getTime();
		updated = new Date().getTime();
		orders = Integer.valueOf(3);
		deliveries = Integer.valueOf(5);
		
		user = new User(username, password, role, email, name, image, icon, achievements, phones, 
				addresses, created, updated, orders, deliveries);
	}
	
	@Test
	public void testUserRegistrationUsernameTaken()
	{
		try
		{
			ResponseEntity<String> receivedResponseEntity = Utils.postUser(user);
			Response response = Utils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
			assertTrue(response.isUsernameTaken(response.getError()));
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	@Test
	public void testUserRegistrationEmailTaken()
	{
		try
		{
			ResponseEntity<String> receivedResponseEntity = Utils.postUser(user);
			Response response = Utils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
			assertTrue(response.isEmailTaken(response.getError()));
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	@Test
	public void testUserRegistrationSucceeds()
	{
		try
		{
			ResponseEntity<String> receivedResponseEntity = Utils.postUser(user);
			Response response = Utils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
			assertTrue(false); // sort this out when server error decided to be String or boolean
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	@Test
	public void testGetOrdersUnauthorized()
	{
		httpStatus = HttpStatus.UNAUTHORIZED;
		try{
			Utils.getOrders();
		}
		catch(Exception e)
		{
			assertTrue(getErrorStatusCode(e).equals(httpStatus));
		}
	}
	
	private HttpStatus getErrorStatusCode(Exception e)
	{
		if(e instanceof HttpClientErrorException)
		{
			return ((HttpClientErrorException) e).getStatusCode();
		}
		else if(e instanceof HttpServerErrorException)
		{
			return ((HttpServerErrorException) e).getStatusCode();
		}
		else if(e instanceof RestClientException)
		{
			return null;
		}
		return null;
	}
}
