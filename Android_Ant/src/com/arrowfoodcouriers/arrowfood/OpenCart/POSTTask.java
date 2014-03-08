package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IPOSTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.LoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.RESTCallback;

public class POSTTask extends AsyncTask<Object, Integer, String>{

	private OpenCartTask _task;
    private RESTCallback _RESTCallback;
    private LoginDialogCallback _loginDialogCallback;
    private IPOSTCall _postCall;

    /**
     * Constructor for POSTs without a dialog listener.
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     */
    public POSTTask(OpenCartTask task, RESTCallback restCallback, IPOSTCall postCall)
    {
        this(task, restCallback, null, postCall);
    }

    /**
     * Constructor for POSTs with a dialog listener.
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     * @param loginDialogCallback The listener waiting to update dialog in UI.
     */
    public POSTTask(OpenCartTask task, RESTCallback restCallback, LoginDialogCallback loginDialogCallback, IPOSTCall postCall)
    {
        _task = task;
        _RESTCallback = restCallback;
        _loginDialogCallback = loginDialogCallback;
        _postCall = postCall;
    }

    public Boolean urlEncodeData = false;

    @Override
    protected void onPreExecute() {
        if(_loginDialogCallback != null)
        {
            _loginDialogCallback.onTaskStart(); // won't always be a dialog listener
        }
    }

	@Override
    protected String doInBackground(Object... objects) {
        return _postCall.POSTToServer(objects);
    }

    @Override
    protected void onPostExecute(String response) {
        _RESTCallback.onTaskCompleted(_task, response);
    }
}
