package com.arrowfoodcouriers.arrowfood.Async;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

public class LoginPOSTTask extends POSTTask 
{
	private ILoginDialogCallback _loginDialogCallback;
	
	public LoginPOSTTask(IRESTCall postCall, ILoginDialogCallback loginDialogCallback)
	{
		super(postCall);
		_loginDialogCallback = loginDialogCallback;
	}
	
	@Override
	protected void onPreExecute() 
	{
		_loginDialogCallback.onTaskStart();  
	}
}
