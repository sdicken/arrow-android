package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartRegistration;

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
    EditText _telephoneField;
    EditText _faxField;
    EditText _companyField;
    EditText _companyidField;
    EditText _address1Field;
    EditText _address2Field;
    EditText _cityField;
    EditText _postalCodeField;
    EditText _countryField;
    EditText _stateField;
    EditText _passwordField;
    EditText _confirmPasswordField;
    RadioGroup _newsletterField;
    CheckBox _privacyPolicyField;
    
    public RegistrationDialogFragment()
    {
    	
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ISession session = ((MainActivity)getActivity()).getSession();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_registration, null);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.registration_activity_circle);
        _firstNameField = (EditText) dialogView.findViewById(R.id.register_first_name);
        _lastNameField = (EditText) dialogView.findViewById(R.id.register_last_name);
        _emailField = (EditText) dialogView.findViewById(R.id.register_email);
        _telephoneField = (EditText) dialogView.findViewById(R.id.register_telephone);
        _faxField = (EditText) dialogView.findViewById(R.id.register_fax);
        _companyField = (EditText) dialogView.findViewById(R.id.register_company);
        _companyidField = (EditText) dialogView.findViewById(R.id.register_companyid);
        _address1Field  = (EditText) dialogView.findViewById(R.id.register_address1);
        _address2Field = (EditText) dialogView.findViewById(R.id.register_address2);
        _cityField = (EditText) dialogView.findViewById(R.id.register_city);
        _postalCodeField = (EditText) dialogView.findViewById(R.id.register_postal_code);
        _countryField = (EditText) dialogView.findViewById(R.id.register_country);
        _stateField = (EditText) dialogView.findViewById(R.id.register_state);
        _passwordField = (EditText) dialogView.findViewById(R.id.register_password);
        _confirmPasswordField = (EditText) dialogView.findViewById(R.id.register_confirm_password);
        _newsletterField = (RadioGroup) dialogView.findViewById(R.id.register_newsletter);
        _privacyPolicyField = (CheckBox) dialogView.findViewById(R.id.register_privacy_policy);

        _alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_register_title)
                .setPositiveButton(R.string.dialog_register, new UnimplementedClickListener())
                .setNegativeButton(R.string.dialog_cancel, new UnimplementedClickListener())
                .create();

        // Prevents automatic dismissal of dialog window on positive button click
        _alertDialog.setOnShowListener(new RegistrationShowListener(session));

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
    	private final ISession _session;
    	public RegistrationShowListener(ISession session)
    	{
    		this._session = session;
    	}
    	
    	public void onShow(DialogInterface dialogInterface) 
        {
            Button positiveButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() 
            {
                public void onClick(View view) 
                {
                    // TODO: put registration code here
                    OpenCartRegistration register = new OpenCartRegistration();
                    String firstName = _firstNameField.getText().toString();
                    String lastName = _lastNameField.getText().toString();
                    String email = _emailField.getText().toString();
                    String telephone = _telephoneField.getText().toString();
                    String fax = _faxField.getText().toString();
                    String company = _companyField.getText().toString();
                    String companyid = _companyidField.getText().toString();
                    String address1 = _address1Field.getText().toString();
                    String address2 = _address2Field.getText().toString();
                    String city = _cityField.getText().toString();
                    String postalCode = _postalCodeField.getText().toString();
                    String country = _countryField.getText().toString();
                    String state = _stateField.getText().toString();
                    String password = _passwordField.getText().toString();
                    String confirmPassword = _confirmPasswordField.getText().toString();
                    String newsletter = new String();
                    switch(_newsletterField.getId())
                    {
                        case R.id.register_newsletter_yes:
                        {
                            newsletter = "1";
                            break;
                        }
                        case R.id.register_newsletter_no:
                        {
                            newsletter = "0";
                            break;
                        }
                    }
                    String privacyPolicy = _privacyPolicyField.isChecked() ? "1" : "0";
                    register.FirstName = firstName;
                    register.LastName = lastName;
                    register.Email = email;
                    register.Telephone = telephone;
                    register.Fax = fax;
                    register.Company = company;
                    register.CompanyId = companyid;
                    register.Address1 = address1;
                    register.Address2 = address2;
//                    register.City = city;
                    register.PostalCode = postalCode;
//                    register.CountryId = country;   // TODO: fix this
//                    register.ZoneId = state;        // TODO: fix this
                    register.Password = password;
                    register.ConfirmPassword = confirmPassword;
                    register.Newsletter = newsletter;
                    register.Agree = privacyPolicy;
                    _session.Register(register);
                }
            });
        }    	
    }
}
