package com.arrowfoodcouriers.arrowfood.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.ListAdapter;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.DrawerValues;
import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Interfaces.LoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest 
{
	@Test
	public void navigationDrawerValues_ChangeAfterLogin()
	{
		MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
		OpenCartSession session = activity.getOpenCartSession();
		session.AttachLoginDialogCallback(new MockLoginCallback());
		ListView mDrawerList = (ListView) activity.findViewById(R.id.left_drawer);
		ListAdapter adapter = mDrawerList.getAdapter();
		List<Object> navigationDrawerItemsBeforeLogin = new ArrayList<Object>();
		for(int i = 0; i < adapter.getCount(); ++i)
		{
			navigationDrawerItemsBeforeLogin.add(adapter.getItem(i));
		}
		session.Login("test@test.test", "test");
		adapter = mDrawerList.getAdapter();
		List<Object> navigationDrawerItemsAfterLogin = new ArrayList<Object>();
		for(int i = 0; i < adapter.getCount(); ++i)
		{
			navigationDrawerItemsAfterLogin.add(adapter.getItem(i));
		}
		assertThat(navigationDrawerItemsBeforeLogin, not(equalTo(navigationDrawerItemsAfterLogin)));
	}
	
	@Test
	public void navigationDrawerValues_matchLoggedOut()
	{
		MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
		OpenCartSession session = activity.getOpenCartSession();
		session.AttachLoginDialogCallback(new MockLoginCallback());
		ListView mDrawerList = (ListView) activity.findViewById(R.id.left_drawer);
		ListAdapter adapter = mDrawerList.getAdapter();
		Object [] navigationDrawerItemsBeforeLogin = new Object[adapter.getCount()];
		for(int i = 0; i < adapter.getCount(); ++i)
		{
			navigationDrawerItemsBeforeLogin[i] = adapter.getItem(i);
		}
		assertThat(navigationDrawerItemsBeforeLogin, equalTo(new DrawerValues(session).getDrawerValues()));
	}
	
	@Test
	public void navigationDrawerValues_matchLoggedIn()
	{
		MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
		OpenCartSession session = activity.getOpenCartSession();
		session.AttachLoginDialogCallback(new MockLoginCallback());
		ListView mDrawerList = (ListView) activity.findViewById(R.id.left_drawer);
		session.Login("test@test.test", "test");
		ListAdapter adapter = mDrawerList.getAdapter();
		Object [] navigationDrawerItemsAfterLogin = new Object [adapter.getCount()];
		for(int i = 0; i < adapter.getCount(); ++i)
		{
			navigationDrawerItemsAfterLogin[i] = adapter.getItem(i);
		}
		assertThat(navigationDrawerItemsAfterLogin, equalTo(new DrawerValues(session).getDrawerValues()));
	}
	
	private class MockLoginCallback implements LoginDialogCallback
	{

		@Override
		public void onTaskStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTaskCompleted(Boolean result) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
