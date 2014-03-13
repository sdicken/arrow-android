package mocks;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartTask;

public class MockRESTCallback implements IRESTCallback
{
	public Boolean completeWasCalled;
	@Override
	public void onTaskCompleted(OpenCartTask task, String response) 
	{
		completeWasCalled = true;
	}
}