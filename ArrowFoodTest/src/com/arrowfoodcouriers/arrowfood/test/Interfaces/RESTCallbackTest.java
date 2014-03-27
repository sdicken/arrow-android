package com.arrowfoodcouriers.arrowfood.test.Interfaces;

import static org.junit.Assert.assertTrue;
import mocks.MockRESTCallback;

import org.junit.Before;
import org.junit.Test;

public class RESTCallbackTest 
{
	private MockRESTCallback _mockRestCallback;
	
	@Before
	public void setUp()
	{
		_mockRestCallback = new MockRESTCallback();
	}
	
	@Test
	public void restCallbackShouldUpdate()
	{
		_mockRestCallback.onTaskCompleted(null, null, null);
		assertTrue(_mockRestCallback.completeWasCalled);
	}
}
