package com.arrowfoodcouriers.arrowfood.Callbacks;

import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Fragments.RegistrationDialogFragment;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.google.inject.Inject;

public class RegistrationDialogCallback implements IRegistrationDialogCallback 
{
	@InjectView(R.id.registration_activity_circle) ProgressBar _progressBar;
	AlertDialog _alertDialog;
	
	@Inject
	public RegistrationDialogCallback()
	{
		this._alertDialog = (AlertDialog) new RegistrationDialogFragment().onCreateDialog(null);
	}
	
	public void onTaskStart()
    {
        _progressBar.setVisibility(View.VISIBLE);
    }

    public void onTaskCompleted(Boolean registrationSuccessful)
    {
        _progressBar.setVisibility(View.GONE);
        if(registrationSuccessful)
        {
            _alertDialog.dismiss();
        }
        else
        {
            // TODO: persist dialog, shake animation, display help text
            TextView retryText = (TextView) _alertDialog.findViewById(R.id.registration_retry);
            retryText.setVisibility(View.VISIBLE);
        }
    }

	public void onSuccess() {
		// TODO Auto-generated method stub
		
	}

	public void onFailure() {
		// TODO Auto-generated method stub
		
	}

	public void onTaskCompleted() {
		// TODO Auto-generated method stub
		
	}
}
