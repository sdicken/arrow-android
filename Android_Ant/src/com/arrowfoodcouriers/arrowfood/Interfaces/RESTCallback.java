package com.arrowfoodcouriers.arrowfood.Interfaces;

import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartTask;

/**
 * Created by Sam on 2/20/14.
 */
public interface RESTCallback
{
    void onTaskCompleted(OpenCartTask task, String response);
}
