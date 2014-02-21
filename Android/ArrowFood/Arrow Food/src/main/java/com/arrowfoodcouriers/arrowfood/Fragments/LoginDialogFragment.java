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
    private Dialog alertDialog = null;

    public LoginDialogFragment(ILoginClass loginClass) {
        this._loginClass = loginClass;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_signin, null);
        final OpenCartSession session = new OpenCartSession(this);
        _progressBar = (ProgressBar) dialogView.findViewById(R.id.login_activity_circle);

        alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_sign_in, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
//                        _loginClass.LoginUser("test", "test");
//                        session.Login("e674501@drdrb.com", "pass");
//                        ListView listView = (ListView) getActivity().findViewById(R.id.left_drawer);
//                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                }).setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        }).create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = ((AlertDialog)alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        session.Login("e674501@drdrb.com", "pass");
                        ListView listView = (ListView) getActivity().findViewById(R.id.left_drawer);
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });
                Button negativeButton = ((AlertDialog)alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            }
        });

        return alertDialog;
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
            alertDialog.dismiss();
        }
        else
        {
            // persist dialog, shake animation, display help text
        }
    }
}
