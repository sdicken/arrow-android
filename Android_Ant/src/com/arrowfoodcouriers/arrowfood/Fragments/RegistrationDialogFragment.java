package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Models.User;

/**
 * Created by Sam on 2/25/14.
 */
public class RegistrationDialogFragment extends DialogFragment implements IRegistrationDialogCallback
{
    private Dialog _alertDialog = null;
    ProgressBar _progressBar;
    EditText _firstNameField;
    EditText _lastNameField;
    EditText _emailField;
    EditText _passwordField;
    EditText _confirmPasswordField;
    
    public RegistrationDialogFragment()
    {
    	
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_registration, null);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.registration_activity_circle);
        _firstNameField = (EditText) dialogView.findViewById(R.id.register_first_name);
        _lastNameField = (EditText) dialogView.findViewById(R.id.register_last_name);
        _emailField = (EditText) dialogView.findViewById(R.id.register_email);
        _passwordField = (EditText) dialogView.findViewById(R.id.register_password);
        _confirmPasswordField = (EditText) dialogView.findViewById(R.id.register_confirm_password);

        _alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_register_title)
                .setPositiveButton(R.string.dialog_register, new UnimplementedClickListener())
                .setNegativeButton(R.string.dialog_cancel, new UnimplementedClickListener())
                .create();

        // Prevents automatic dismissal of dialog window on positive button click
        _alertDialog.setOnShowListener(new RegistrationShowListener());

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
        TextView retryText = (TextView) _alertDialog.findViewById(R.id.registration_retry);
        retryText.setVisibility(View.VISIBLE);
    }
    
    private class UnimplementedClickListener implements DialogInterface.OnClickListener
    {
		public void onClick(DialogInterface dialog, int which) 
		{
		}	
    }
    
    private class RegistrationShowListener implements DialogInterface.OnShowListener
    {
    	private RESTUtils utils;
    	
    	public RegistrationShowListener()
    	{
    		utils = new RESTUtils();
    	}
    	
    	public void onShow(DialogInterface dialogInterface) 
        {
            Button positiveButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() 
            {
                public void onClick(View view) 
                {
                    // TODO: put registration code here
                    String firstName = _firstNameField.getText().toString();
                    String lastName = _lastNameField.getText().toString();
                    String email = _emailField.getText().toString();
                    String password = _passwordField.getText().toString();
                    String confirmPassword = _confirmPasswordField.getText().toString();
//                    User user = new User(username, password, email, name);
//                    utils.postUser(user);
                }
            });
        }    	
    }
}
