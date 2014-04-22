package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.List;

import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RestaurantRequestListener;
import com.arrowfoodcouriers.arrowfood.Loaders.RestaurantLoader;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RestaurantRequest;

public class RestaurantFragment extends ListFragment 
{
    RestaurantListAdapter mAdapter;
    public static final String RESTAURANT_NAME = "restaurantName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	RestaurantRequest request = new RestaurantRequest();
        MainActivity.spiceManager.execute(request, new RestaurantRequestListener(getActivity()));

        getLoaderManager().initLoader(4, null, new RestaurantLoaderCallback(getActivity()));
        
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	TextView restaurantNameView = (TextView) v.findViewById(R.id.restaurant_list_title);
    	Fragment fragment = new RestaurantMenuCategoryFragment();
    	Bundle args = new Bundle();
    	args.putString(RESTAURANT_NAME, restaurantNameView.getText().toString());
    	fragment.setArguments(args);
		Utils.loadFragment(getFragmentManager(), fragment);
    	super.onListItemClick(l, v, position, id);
    }
    
    private class RestaurantLoaderCallback implements LoaderManager.LoaderCallbacks<List<Restaurant>>
    {
    	private Context context;
    	
    	public RestaurantLoaderCallback(Context context)
    	{
    		this.context = context;
    	}
    	
    	@Override
    	public Loader<List<Restaurant>> onCreateLoader(int id, Bundle args) {
    		return new RestaurantLoader(context);
    	}

    	@Override
    	public void onLoadFinished(Loader<List<Restaurant>> loader, List<Restaurant> data) {
    		if (data != null) {
    			setListAdapter(new RestaurantListAdapter(context, data));
    		}
    		else {
    			setListAdapter(null);
    		}
    	}

    	@Override
    	public void onLoaderReset(Loader<List<Restaurant>> loader) {
    		setListAdapter(null);
    	}    	
    	
    }
    

}
