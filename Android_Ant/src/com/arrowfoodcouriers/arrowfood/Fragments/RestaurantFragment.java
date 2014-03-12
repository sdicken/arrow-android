package com.arrowfoodcouriers.arrowfood.Fragments;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.RestaurantListAdapter;

public class RestaurantFragment extends ListFragment {

    RestaurantListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAdapter = new RestaurantListAdapter();
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }

}
