package com.arrowfoodcouriers.arrowfood.OpenCart;

import com.arrowfoodcouriers.arrowfood.Interfaces.IPOSTCall;

public class MockPOSTCall implements IPOSTCall {
	
	private static final String MOCK_RESPONSE = "";

	public String POSTToServer(Object... objects) {
		return MOCK_RESPONSE;
	}

}
