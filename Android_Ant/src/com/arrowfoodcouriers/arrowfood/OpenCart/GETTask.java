package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;

/**
 * Android-based threading abstraction class used for GETs.
 */
public class GETTask extends AsyncTask<Object, Integer, String>
{
    private OpenCartTask _task;
    private IRESTCallback _RESTCallback;
    private IRESTCall _restCall;
    private ISession _session;

    /**
     *
     * @param task The task for which the GET is being executed
     * @param restCallback The listener waiting for task completion callback
     * @param restCall A class containing GET business logic
     * @param session A session interface object for managing session updates after {@link #onPostExecute(String)}
     */
    public GETTask(OpenCartTask task, IRESTCallback restCallback, IRESTCall restCall, ISession session)
    {
        _task = task;
        _RESTCallback = restCallback;
        _restCall = restCall;
        _session = session;
    }

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
