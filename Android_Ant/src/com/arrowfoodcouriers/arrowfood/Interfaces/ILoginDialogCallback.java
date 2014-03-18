package com.arrowfoodcouriers.arrowfood.Interfaces;


/**
 * Created by Sam on 2/21/14.
 */
public interface ILoginDialogCallback
{
	void attachRegistrationDialogCallback(IRegistrationDialogCallback registrationDialogCallback);
    void onTaskStart();
    void onTaskCompleted();
    void onSuccess();
    void onFailure();
}
