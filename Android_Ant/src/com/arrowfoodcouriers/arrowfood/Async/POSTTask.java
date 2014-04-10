package com.arrowfoodcouriers.arrowfood.Async;

import android.os.AsyncTask;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;

public class POSTTask extends AsyncTask<Object, Integer, String>
{
    private IRESTCall _restCall;

    /**
     * Constructor for POSTs without a dialog listener.
     * @param postCall A class containing the business logic for POST
     * @param session A session interface object for session updates in {@link #onPostExecute(String)}
     */
    public POSTTask(IRESTCall postCall)
    {
        _restCall = postCall;
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
    }
}
