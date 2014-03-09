package com.arrowfoodcouriers.arrowfood.test.Interfaces;

import static org.junit.Assert.assertTrue;
import mocks.MockLoginCallback;

import org.junit.Before;
import org.junit.Test;

public class LoginDialogCallbackTest 
{
	private MockLoginCallback _loginCallback;
	
	@Before
	public void setUp()
	{
		_loginCallback = new MockLoginCallback();
	}
	
	@Test
	public void submitLogin_authFailure()
	{
		_loginCallback.onFailure();
		assertTrue(_loginCallback.failureWasCalled);
	}
	
	@Test
	public void submitLogin_authSuccess()
	{
		_loginCallback.onSuccess();
		assertTrue(_loginCallback.successWasCalled);
	}
}
