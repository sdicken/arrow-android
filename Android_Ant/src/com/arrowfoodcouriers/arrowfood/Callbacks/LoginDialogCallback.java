package com.arrowfoodcouriers.arrowfood.Callbacks;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Fragments.LoginDialogFragment;
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.google.inject.Inject;

public class LoginDialogCallback implements ILoginDialogCallback 
{
	ProgressBar _progressBar;
	AlertDialog _alertDialog;
	
	@Inject
	public LoginDialogCallback(LoginDialogFragment fragment)
	{
		this._alertDialog = (AlertDialog) fragment.getDialog();
		this._progressBar = (ProgressBar) fragment.getDialog().findViewById(R.id.login_activity_circle);
	}

	public void onTaskStart() 
    {
        _progressBar.setVisibility(View.VISIBLE);
    }

    public void onTaskCompleted() 
    {
        _progressBar.setVisibility(View.GONE);
    }

	public void onSuccess() 
	{
		_alertDialog.dismiss();
		
	}

	public void onFailure() 
	{
		// TODO: persist dialog, shake animation, display help text
        TextView retryText = (TextView) _alertDialog.findViewById(R.id.login_auth_retry);
        retryText.setVisibility(View.VISIBLE);
	}
}
