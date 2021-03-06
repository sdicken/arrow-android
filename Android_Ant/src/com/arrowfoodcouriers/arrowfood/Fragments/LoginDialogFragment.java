package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.RoboSpice.LoginRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Ryan on 2/17/14.
 */
public class LoginDialogFragment extends DialogFragment
{
    ProgressBar _progressBar;
    private Dialog _alertDialog = null;
    private EditText _usernameField;
    private EditText _passwordField;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_signin, null);
        _usernameField = (EditText) dialogView.findViewById(R.id.login_username);
        _passwordField = (EditText) dialogView.findViewById(R.id.login_password);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.login_activity_circle);

        _alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_login_title)
                .setPositiveButton(R.string.dialog_sign_in, new UnimplementedOnClickListener())
                .setNegativeButton(R.string.dialog_register, new UnimplementedOnClickListener())
                .setNeutralButton(R.string.dialog_forgot_password, new UnimplementedOnClickListener())
                .create();

        // Prevents automatic dismissal of dialog window on positive button click
        _alertDialog.setOnShowListener(new LoginShowDialogListener());

        return _alertDialog;
    }
	
	private class UnimplementedOnClickListener implements DialogInterface.OnClickListener
	{
		public void onClick(DialogInterface dialog, int which) 
		{			
		}		
	}
	
	private class LoginShowDialogListener implements DialogInterface.OnShowListener
	{
		public void onShow(DialogInterface dialogInterface) 
        {
            Button positiveButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() 
            {
                public void onClick(View view) 
                {
                	_progressBar.setVisibility(View.VISIBLE);
                    String username = _usernameField.getText().toString();
                    String password = _passwordField.getText().toString();
                    LoginRequest request = new LoginRequest(username, password);
                    MainActivity.spiceManager.execute(request, new LoginRequestListener());
                }
            });
            Button negativeButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() 
            {
                
                public void onClick(View view) 
                {
                    // TODO: put registration code here
                    _alertDialog.dismiss();
                    FragmentManager fragmentManager = getFragmentManager();
                    new RegistrationDialogFragment().show(fragmentManager, MainActivity.FRAGMENT_TAG_REGISTER);
                }
            });
            Button neutralButton = ((AlertDialog)_alertDialog).getButton(AlertDialog.BUTTON_NEUTRAL);
            neutralButton.setOnClickListener(new View.OnClickListener() 
            {
                
                public void onClick(View view) 
                {
                    // TODO: put forgot password code here (when implemented)
                }
            });
        }		
	}
	
	private class LoginRequestListener implements RequestListener<Response>
	{
		@Override
		public void onRequestFailure(SpiceException e) 
		{
			Log.d("robospice", "failure");
			_progressBar.setVisibility(View.GONE);
			// TODO: persist dialog, shake animation, display help text
	        TextView retryText = (TextView) _alertDialog.findViewById(R.id.login_auth_retry);
	        retryText.setVisibility(View.VISIBLE);
		}

		@Override
		public void onRequestSuccess(Response response) 
		{
			Log.d("robospice","success");
			_progressBar.setVisibility(View.GONE);
			_alertDialog.dismiss();
			
			Intent intent = new Intent();
			intent.setAction("com.arrowfoodcouriers.arrowfood.ACTION_LOGIN");
			intent.putExtra("logged_on", true);
			getActivity().sendBroadcast(intent);
		}
	}
}
