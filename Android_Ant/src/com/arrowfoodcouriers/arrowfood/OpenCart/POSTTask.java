package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;

public class POSTTask extends AsyncTask<Object, Integer, String>
{

	private OpenCartTask _task;
    private IRESTCallback _RESTCallback;
    private IRESTCall _restCall;
    private ISession _session;

    /**
     * Constructor for POSTs without a dialog listener.
     * @param task The task for which the POST is being executed
     * @param restCallback The listener waiting for task completion callback
     * @param postCall A class containing the business logic for POST
     * @param session A session interface object for session updates in {@link #onPostExecute(String)}
     */
    public POSTTask(OpenCartTask task, IRESTCallback restCallback, IRESTCall postCall, ISession session)
    {
        _task = task;
        _RESTCallback = restCallback;
        _restCall = postCall;
        _session = session;
    }

    public Boolean urlEncodeData = false;

	@Override
    protected String doInBackground(Object... objects) 
	{
        return _restCall.makeRequestToServer(objects);
    }

    @Override
    protected void onPostExecute(String response) 
    {
        _RESTCallback.onTaskCompleted(_task, _session, response);
    }
}
