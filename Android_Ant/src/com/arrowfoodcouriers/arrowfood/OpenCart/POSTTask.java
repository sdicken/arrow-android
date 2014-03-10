package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;

public class POSTTask extends AsyncTask<Object, Integer, String>{

	private OpenCartTask _task;
    private IRESTCallback _RESTCallback;
    private ILoginDialogCallback _loginDialogCallback;
    private IRESTCall _restCall;

    /**
     * Constructor for POSTs without a dialog listener.
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     */
    public POSTTask(OpenCartTask task, IRESTCallback restCallback, IRESTCall restCall)
    {
        this(task, restCallback, null, restCall);
    }

    /**
     * Constructor for POSTs with a dialog listener.
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     * @param loginDialogCallback The listener waiting to update dialog in UI.
     */
    public POSTTask(OpenCartTask task, IRESTCallback restCallback, ILoginDialogCallback loginDialogCallback, IRESTCall postCall)
    {
        _task = task;
        _RESTCallback = restCallback;
        _loginDialogCallback = loginDialogCallback;
        _restCall = postCall;
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
        return _restCall.makeRequestToServer(objects);
    }

    @Override
    protected void onPostExecute(String response) {
        _RESTCallback.onTaskCompleted(_task, response);
    }
}
