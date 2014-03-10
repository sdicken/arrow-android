package mocks;

import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;

public class MockNavigationDrawerCallback implements INavigationDrawerCallback 
{
	public Boolean updateWasCalled;
	@Override
	public void onNavigationDrawerUpdated() 
	{
		updateWasCalled = true;
	}

}
