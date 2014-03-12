package com.arrowfoodcouriers.arrowfood.OpenCart;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;

public class LoginPOSTTask extends POSTTask 
{
	private ILoginDialogCallback _loginDialogCallback;
	
	public LoginPOSTTask(IRESTCallback restCallback, IRESTCall postCall, ILoginDialogCallback loginDialogCallback, ISession session)
	{
		super(OpenCartTask.LOGIN, restCallback, postCall, session);
		_loginDialogCallback = loginDialogCallback;
	}
	
	@Override
	protected void onPreExecute() 
	{
		_loginDialogCallback.onTaskStart();  
	}
}
