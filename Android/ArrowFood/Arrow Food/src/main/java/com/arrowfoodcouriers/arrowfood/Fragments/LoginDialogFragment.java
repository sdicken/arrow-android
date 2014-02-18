package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginClass;
import com.arrowfoodcouriers.arrowfood.LoginClass;
import com.arrowfoodcouriers.arrowfood.R;

/**
 * Created by Ryan on 2/17/14.
 */
public class LoginDialogFragment extends DialogFragment {

    public ILoginClass _loginClass;

    public LoginDialogFragment(ILoginClass loginClass) {
        this._loginClass = loginClass;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_sign_in, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _loginClass.LoginUser("test", "test");
                        ListView listView = (ListView)getActivity().findViewById(R.id.left_drawer);
                        ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
                    }
                }).setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
}
