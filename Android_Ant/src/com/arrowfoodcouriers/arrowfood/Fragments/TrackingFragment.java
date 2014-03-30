package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.Fragment;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TrackingFragment extends Fragment
{
	private GoogleMap map;
//	private MapFragment mapFragment;
	private MapView mapView;
	private TextView statusText1;
	private TextView statusText2;
	private TextView statusText3;
	private TextView statusText4;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		MapsInitializer.initialize(getActivity());
		mapView = new MapView(getActivity());
		mapView.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_tracking, container, false);
		statusText1 = (TextView) view.findViewById(R.id.tracking_status_text1);
		statusText2 = (TextView) view.findViewById(R.id.tracking_status_text2);
		statusText3 = (TextView) view.findViewById(R.id.tracking_status_text3);
		statusText4 = (TextView) view.findViewById(R.id.tracking_status_text4);
		
		transitionToActivated(statusText1);	// demonstrate how this will work when hooked up
		
		setUpMapIfNeeded(view);
		
		// Inflate the layout for this fragment
        return view;
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	public void onPause() 
	{
		super.onPause();
		mapView.onPause();
	}
	
	@Override
	public void onDestroy() 
	{
		super.onDestroy();
		mapView.onDestroy();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}
	
	@Override
	public void onLowMemory() 
	{
		super.onLowMemory();
		mapView.onLowMemory();
	}
	
	private void transitionToActivated(TextView statusText)
	{
		TransitionDrawable transition = (TransitionDrawable) statusText.getCompoundDrawables()[1];	// get Drawable attached to top of TextView
		final int duration = 500;
		transition.startTransition(duration);
	}
	
	private void transitionToDeactivated(TextView statusText)
	{
		TransitionDrawable transition = (TransitionDrawable) statusText.getCompoundDrawables()[1];	// get Drawable attached to top of TextView
		transition.reverseTransition(500);
	}
	
	// modified from https://developers.google.com/maps/documentation/android/map
	private void setUpMapIfNeeded(View view) 
	{
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) 
	    {
			((ViewGroup)view.findViewById(R.id.map)).addView(mapView);
			map = mapView.getMap();
	        // Check if we were successful in obtaining the map.
	        if (map != null) 
	        {
	            // The Map is verified. It is now safe to manipulate the map.
	        	final LatLng uOfLLocation = new LatLng(38.2153349, -85.7620279);
	    		CameraUpdate moveToUofLLocation = CameraUpdateFactory.newLatLng(uOfLLocation);
	    		map.moveCamera(moveToUofLLocation);
	    		CameraUpdate zoomInOnUofLLocation = CameraUpdateFactory.zoomTo(16f);
	    		map.moveCamera(zoomInOnUofLLocation);
	    		String foodLocationMarkerText = getResources().getString(R.string.maps_marker_food_location);
	    		map.addMarker(new MarkerOptions().position(uOfLLocation)
	    				.title(foodLocationMarkerText));
	        }
	    }
	}

}
