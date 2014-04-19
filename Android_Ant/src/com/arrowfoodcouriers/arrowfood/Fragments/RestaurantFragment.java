package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;

public class RestaurantFragment extends ListFragment 
{
	private static final int QDOBA_POSITION = 0;
	private static final int QUILLS_POSITION = 1;
    RestaurantListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        mAdapter = new RestaurantListAdapter(inflater.getContext(), restaurants);
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_restaurant, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	TextView restaurantNameView = (TextView) v.findViewById(R.id.restaurant_list_title);
    	Fragment fragment = new MenuFragment(restaurantNameView.getText().toString(), "Breakfast");
		Utils.loadFragment(getFragmentManager(), fragment);
    	super.onListItemClick(l, v, position, id);
    }
    

}
