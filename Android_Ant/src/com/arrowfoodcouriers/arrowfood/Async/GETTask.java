package com.arrowfoodcouriers.arrowfood.Async;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

/**
 * Android-based threading abstraction class used for GETs.
 */
public class GETTask extends AsyncTask<Object, Integer, String>
{
    private IRESTCall _restCall;

    /**
     *
     * @param restCall A class containing GET business logic
     */
    public GETTask(IRESTCall restCall)
    {
        _restCall = restCall;
    }

    @Override
    protected String doInBackground(Object... objects) 
    {
        return _restCall.makeRequestToServer(objects);
    }

    @Override
    protected void onPostExecute(String response) 
    {
    }
}
