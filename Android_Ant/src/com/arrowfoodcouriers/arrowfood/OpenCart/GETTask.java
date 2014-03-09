package com.arrowfoodcouriers.arrowfood.OpenCart;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.RESTCallback;

/**
 * Android-based threading abstraction class used for GETs.
 */
public class GETTask extends AsyncTask<Object, Integer, String>
{
    private OpenCartTask _task;
    private RESTCallback _RESTCallback;
    private IRESTCall _restCall;

    /**
     *
     * @param task The task the POST is being executed for.
     * @param restCallback The listener waiting for task completion callback.
     */
    public GETTask(OpenCartTask task, RESTCallback restCallback, IRESTCall restCall)
    {
        _task = task;
        _RESTCallback = restCallback;
        _restCall = restCall;
    }

    @Override
    protected String doInBackground(Object... objects) 
    {
        return _restCall.makeRequestToServer(objects);
    }

    @Override
    protected void onPostExecute(String response) {
        _RESTCallback.onTaskCompleted(_task, response);
    }
}
