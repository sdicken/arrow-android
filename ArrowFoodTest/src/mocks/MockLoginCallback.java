package mocks;

import com.arrowfoodcouriers.arrowfood.Interfaces.LoginDialogCallback;

public class MockLoginCallback implements LoginDialogCallback 
{
	public Boolean startWasCalled;
	public Boolean completeWasCalled;
	public Boolean successWasCalled;
	public Boolean failureWasCalled;

	@Override
	public void onTaskStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTaskCompleted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess() 
	{
		successWasCalled = true;
	}

	@Override
	public void onFailure() 
	{
		failureWasCalled = true;
	}

}
