package mocks;

import com.arrowfoodcouriers.arrowfood.Interfaces.NavigationDrawerCallback;

public class MockNavigationDrawerCallback implements NavigationDrawerCallback 
{
	public Boolean updateWasCalled;
	@Override
	public void onNavigationDrawerUpdated() 
	{
		updateWasCalled = true;
	}

}
