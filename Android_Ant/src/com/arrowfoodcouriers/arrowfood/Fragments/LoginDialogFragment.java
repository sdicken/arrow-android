package com.arrowfoodcouriers.arrowfood.Fragments;

import roboguice.fragment.RoboDialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;
import com.google.inject.Inject;

/**
 * Created by Ryan on 2/17/14.
 */
public class LoginDialogFragment extends RoboDialogFragment implements ILoginDialogCallback
{
    ProgressBar _progressBar;
    private Dialog _alertDialog = null;
    EditText _usernameField;
    EditText _passwordField;
    private IRegistrationDialogCallback _registrationDialogCallback;
//    @Inject RegistrationDialogFragment fragment;
    
    @Inject
    public LoginDialogFragment(IRegistrationDialogCallback registrationDialogCallback)
    {
    	this._registrationDialogCallback = registrationDialogCallback;
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ISession session = ((MainActivity)getActivity()).getOpenCartSession();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_signin, null);
//        _session = ((MainActivity)getActivity()).getOpenCartSession();
//        _session.AttachLoginDialogCallback(this);
        _usernameField = (EditText) dialogView.findViewById(R.id.login_username);
        _passwordField = (EditText) dialogView.findViewById(R.id.login_password);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.login_activity_circle);

        _alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_sign_in, new UnimplementedOnClickListener())
                .setNegativeButton(R.string.dialog_register, new UnimplementedOnClickListener())
                .setNeutralButton(R.string.dialog_forgot_password, new UnimplementedOnClickListener())
                .create();

        // Prevents automatic dismissal of dialog window on positive button click
        _alertDialog.setOnShowListener(new LoginShowDialogListener(session));

        return _alertDialog;
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
	
	private class UnimplementedOnClickListener implements DialogInterface.OnClickListener
	{
		public void onClick(DialogInterface dialog, int which) 
		{			
		}		
	}
	
	private class LoginShowDialogListener implements DialogInterface.OnShowListener
	{
		private final ISession _session;
		public LoginShowDialogListener(ISession session)
		{
			_session = session;
		}
		
		public void onShow(DialogInterface dialogInterface) 
        {
            Button positiveButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() 
            {
               
                public void onClick(View view) 
                {
                    String username = _usernameField.getText().toString();
                    String password = _passwordField.getText().toString();
                    _session.Login(username, password);
                }
            });
            Button negativeButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() 
            {
                
                public void onClick(View view) 
                {
                    // TODO: put registration code here
                    _alertDialog.dismiss();
//                    DialogFragment fragment = new RegistrationDialogFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    ((RegistrationDialogFragment)_registrationDialogCallback).show(fragmentManager, "register");
//                    fragment.show(fragmentManager, "register");
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
}
