package com.arrowfoodcouriers.arrowfood.Fragments;

import com.arrowfoodcouriers.arrowfood.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CartFragment extends ListFragment 
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		return view;
	}
}
