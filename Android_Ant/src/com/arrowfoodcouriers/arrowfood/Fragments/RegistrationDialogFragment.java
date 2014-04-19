package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RegistrationRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Sam on 2/25/14.
 */
public class RegistrationDialogFragment extends DialogFragment
{
    private Dialog alertDialog = null;
    private ProgressBar progressBar;
    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText fullNameField;
    private EditText telephoneField;
    private EditText address1Field;
    private EditText address2Field;
    private EditText cityField;
    private EditText stateField;
    private EditText zipField;
    private EditText confirmPasswordField;
    
    public RegistrationDialogFragment()
    {
    	
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_registration, null);
        progressBar = (ProgressBar) dialogView.findViewById(R.id.registration_activity_circle);
        usernameField = (EditText) dialogView.findViewById(R.id.register_username);
        fullNameField = (EditText) dialogView.findViewById(R.id.register_full_name);
        emailField = (EditText) dialogView.findViewById(R.id.register_email);
        passwordField = (EditText) dialogView.findViewById(R.id.register_password);
        telephoneField = (EditText) dialogView.findViewById(R.id.register_telephone);
        address1Field = (EditText) dialogView.findViewById(R.id.register_address1);
        address2Field = (EditText) dialogView.findViewById(R.id.register_address2);
        cityField = (EditText) dialogView.findViewById(R.id.register_city);
        stateField = (EditText) dialogView.findViewById(R.id.register_state);
        zipField = (EditText) dialogView.findViewById(R.id.register_postal_code);
        confirmPasswordField = (EditText) dialogView.findViewById(R.id.register_confirm_password);

        alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_register_title)
                .setPositiveButton(R.string.dialog_register, new UnimplementedClickListener())
                .setNegativeButton(R.string.dialog_cancel, new UnimplementedClickListener())
                .create();

        // Prevents automatic dismissal of dialog window on positive button click
        alertDialog.setOnShowListener(new RegistrationShowListener());

        return alertDialog;
    }
    
    private class UnimplementedClickListener implements DialogInterface.OnClickListener
    {
		public void onClick(DialogInterface dialog, int which) 
		{
		}	
    }
    
    private class RegistrationShowListener implements DialogInterface.OnShowListener
    {    	
    	public void onShow(DialogInterface dialogInterface) 
        {
            Button positiveButton = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() 
            {
                public void onClick(View view) 
                {
                    // TODO: put registration code here
                	progressBar.setVisibility(View.VISIBLE);
                    String username = usernameField.getText().toString();
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();
                    String fullName = fullNameField.getText().toString();
                    String telephone = telephoneField.getText().toString();
                    String address1 = address1Field.getText().toString();
                    String address2 = address2Field.getText().toString();
                    String city = cityField.getText().toString();
                    String state = stateField.getText().toString();
                    String zip = zipField.getText().toString();
                    String confirmPassword = confirmPasswordField.getText().toString();
                    Phone phone = new Phone("Mobile", telephone);
                    Address address = new Address(address1, address2, city, state, zip);
                    if(!password.equals(confirmPassword))
                    {
                    	// TODO: logic goes here
                    }
                    else
                    {
                      RegistrationRequest request = new RegistrationRequest(username, password, email, fullName, address, phone);
                      MainActivity.spiceManager.execute(request, new RegistrationRequestListener());
                    }                    
                }
            });
        }    	
    }
    
    private class RegistrationRequestListener implements RequestListener<Response>
    {
		@Override
		public void onRequestFailure(SpiceException e) 
		{
			Log.d("robospice", "failure");
			progressBar.setVisibility(View.GONE);
			// TODO: persist dialog, shake animation, display help text
	        TextView retryText = (TextView) alertDialog.findViewById(R.id.registration_retry);
	        retryText.setVisibility(View.VISIBLE);
		}

		@Override
		public void onRequestSuccess(Response response) 
		{
			Log.d("robospice", "success");
			progressBar.setVisibility(View.GONE);
			alertDialog.dismiss();
		}
    	
    }
}
