package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.octo.android.robospice.request.SpiceRequest;

public class MenuRequest extends SpiceRequest<List<Menu>> 
{
	
	@SuppressWarnings("unchecked")
	public MenuRequest() 
	{
		super((Class<List<Menu>>) new ArrayList<Menu>().getClass());
	}

	@Override
	public List<Menu> loadDataFromNetwork() throws Exception 
	{
		return Arrays.asList(RESTUtils.getMenus());
	}

}
