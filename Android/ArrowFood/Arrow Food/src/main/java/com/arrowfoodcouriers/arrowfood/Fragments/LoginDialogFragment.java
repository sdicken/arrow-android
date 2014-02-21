package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginClass;
import com.arrowfoodcouriers.arrowfood.LoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;
import com.arrowfoodcouriers.arrowfood.R;

/**
 * Created by Ryan on 2/17/14.
 */
public class LoginDialogFragment extends DialogFragment implements LoginDialogCallback {

    public ILoginClass _loginClass;
    private ProgressBar _progressBar;
    private Dialog _alertDialog = null;
    private EditText _usernameField;
    private EditText _passwordField;

    public LoginDialogFragment(ILoginClass loginClass) {
        this._loginClass = loginClass;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_signin, null);
        final OpenCartSession session = new OpenCartSession(this);
        _usernameField = (EditText) dialogView.findViewById(R.id.username);
        _passwordField = (EditText) dialogView.findViewById(R.id.password);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.login_activity_circle);

        _alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_sign_in, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    } // disable default behavior of close on positiveButton
                }).setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                }).create();

        // Prevents automatic dismissal of dialog window on positive button click
        _alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = _usernameField.getText().toString();
                        String password = _passwordField.getText().toString();
                        session.Login(username, password);
                        ListView listView = (ListView) getActivity().findViewById(R.id.left_drawer);
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });
                Button negativeButton = ((AlertDialog) _alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            }
        });

        return _alertDialog;
    }

    @Override
    public void onTaskStart() {
        _progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTaskCompleted(Boolean result) {
        _progressBar.setVisibility(View.GONE);
        if(result)
        {
            _alertDialog.dismiss();
        }
        else
        {
            // TODO: persist dialog, shake animation, display help text
        }
    }
}
