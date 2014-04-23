package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ListFragment;
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
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RestaurantsRequestListener;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RestaurantsUpdatedRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.RestaurantsUpdatedRequestListener;

public class RestaurantFragment extends ListFragment 
{
    RestaurantListAdapter mAdapter;
    public static final String RESTAURANT_NAME = "restaurantName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	// check to see when restaurants last updated and tell it what to do with the data it gets from cache or network
    	RestaurantsUpdatedRequest request = new RestaurantsUpdatedRequest();
        MainActivity.spiceManager.execute(request, new RestaurantsUpdatedRequestListener<>(new RestaurantsRequestListener(getActivity())));

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        mAdapter = new RestaurantListAdapter(getActivity(), restaurants);
        setListAdapter(mAdapter);
        
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	TextView restaurantNameView = (TextView) v.findViewById(R.id.restaurant_list_title);
    	Fragment fragment = new MenuCategoryFragment();
    	Bundle args = new Bundle();
    	args.putString(RESTAURANT_NAME, restaurantNameView.getText().toString());
    	fragment.setArguments(args);
		Utils.loadFragment(getFragmentManager(), fragment);
    	super.onListItemClick(l, v, position, id);
    } 
}
