package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.util.Date;
import java.util.concurrent.Future;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;

public class MenusRequestWithCacheLogic extends MenusRequest 
{
	private final Date updated;
	
	/**
	 * Constructor called when checking for more recent data
	 * @param updated
	 */
	public MenusRequestWithCacheLogic(Date updated) 
	{
		super();
		this.updated = updated;
	}

	@Override
	public Menu[] loadDataFromNetwork() throws Exception 
	{
		if(updated == null)
		{
			return RESTUtils.getMenus();
		}
		else
		{
			Future<Menu[]> future = MainActivity.spiceManager.getDataFromCache(Menu[].class, createCacheKey());
			Menu[] menus = future.get();
			Date oldestUpdatedInCache = FilterUtils.getOldestUpdated(menus);
			if(updated.after(oldestUpdatedInCache))
			{
				return RESTUtils.getMenus();	// data in cache is stale, get fresh data
			}
			else
			{
				return menus;	// use cached data
			}
		}
	}
}
