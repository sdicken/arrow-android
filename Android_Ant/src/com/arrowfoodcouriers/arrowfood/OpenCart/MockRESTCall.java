package com.arrowfoodcouriers.arrowfood.OpenCart;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

/**
 * Override this class to provide a custom response when testing
 * @author Sam
 *
 */
public class MockRESTCall implements IRESTCall 
{
	
	private static final String MOCK_RESPONSE = "";

	public String makeRequestToServer(Object... objects) 
	{
		return MOCK_RESPONSE;
	}

}
