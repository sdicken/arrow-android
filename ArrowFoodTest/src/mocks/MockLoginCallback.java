package mocks;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;

public class MockLoginCallback implements ILoginDialogCallback 
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

	@Override
	public void attachRegistrationDialogCallback(
			IRegistrationDialogCallback registrationDialogCallback) {
		// TODO Auto-generated method stub
		
	}

}
