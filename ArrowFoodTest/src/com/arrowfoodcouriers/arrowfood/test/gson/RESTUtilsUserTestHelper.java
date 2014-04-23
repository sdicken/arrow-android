package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.Models.User;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsUserTestHelper 
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
	private Date created;
	private Date updated;
	private Integer orders;
	private Integer deliveries;

	private HttpStatus httpStatus;
	
	public User createUserForLogin()
	{
		username = new String("bob");
		password = new String("pass");
		return new User(username, password);
	}
	
	public User createUserForRegistration()
	{
		username = new String("bob");
		password = new String("pass");
		email = new String("bob@bob.bob");
		name = new String("Bobby");
		
		return new User(username, password, email, name);
	}
	
	public User createUser()
	{
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
		created = new Date();
		updated = new Date();
		orders = Integer.valueOf(3);
		deliveries = Integer.valueOf(5);
		
		return new User(username, password, role, email, name, image, icon, achievements, phones, 
				addresses, created, updated, orders, deliveries);
	}
	
	public void testUserRegistrationUsernameTaken()
	{
		User user = createUserForRegistration();
		try
		{
			ResponseEntity<String> receivedResponseEntity = RESTUtils.postUser(user);
			Response response = RESTUtils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
			assertTrue(response.isUsernameTaken(response.getError()));
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	public void testUserRegistrationEmailTaken()
	{
		User user = createUserForRegistration();
		try
		{
			ResponseEntity<String> receivedResponseEntity = RESTUtils.postUser(user);
			Response response = RESTUtils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
			assertTrue(response.isEmailTaken(response.getError()));
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	public void testUserRegistrationSucceeds()
	{
		User user = createUserForRegistration();
		//user.setUsername("bob1"); // need to change this each time or else delete from db after each run
		//user.setEmail("bob1@bob.bob");	// need to change this each time or else delete from db after each run
		try
		{
			ResponseEntity<String> receivedResponseEntity = RESTUtils.postUser(user);
			Response response = RESTUtils.convertResponseEntityToModel(receivedResponseEntity, Response.class);
//			assertTrue(false); // sort this out when server error decided to be String or boolean
		}
		catch(Exception e)
		{
			getErrorStatusCode(e);
		}
	}
	
	
	public void testLoginSuccess()
	{
		User user = createUserForLogin();
		httpStatus = HttpStatus.OK;
		RESTUtils.postLogin(user.getUsername(), user.getPassword());
	}
	
	public void testLoginFailure()
	{
		User user = createUserForLogin();
		user.setUsername("a");
		httpStatus = HttpStatus.NOT_FOUND;
		try
		{
			RESTUtils.postLogin(user.getUsername(), user.getPassword());
		}
		catch(Exception e)
		{
			assertTrue(getErrorStatusCode(e).equals(httpStatus));
		}
	}
	
	public void testLoginMalformed()
	{
		User user = createUserForLogin();
		user.setUsername("");
		httpStatus = HttpStatus.BAD_REQUEST;
		try
		{
			RESTUtils.postLogin(user.getUsername(), user.getPassword());
		}
		catch(Exception e)
		{
			assertTrue(getErrorStatusCode(e).equals(httpStatus));
		}
	}

	public void testGetOrdersUnauthorized()
	{
		httpStatus = HttpStatus.UNAUTHORIZED;
		try{
			RESTUtils.getOrders();
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
