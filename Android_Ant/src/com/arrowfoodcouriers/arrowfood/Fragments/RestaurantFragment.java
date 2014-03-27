package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;

public class RestaurantFragment extends ListFragment 
{
	private static final int QDOBA_POSITION = 0;
	private static final int QUILLS_POSITION = 1;
    RestaurantListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {

        mAdapter = new RestaurantListAdapter();
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	switch(position)
    	{
	    	case QDOBA_POSITION:
	    	{
	    		Fragment fragment = new MenuFragment();
	    		FragmentManager fragmentManager = getFragmentManager();
	    		fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
	    		break;
	    	}
	    	case QUILLS_POSITION:
	    	{
	    		break;
	    	}
    	}
    	super.onListItemClick(l, v, position, id);
    }
    

}
