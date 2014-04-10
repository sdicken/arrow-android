package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

@RunWith(RobolectricTestRunner.class)
public class UserLoaderTest 
{
	@Test
	public void testLoadedDataMatchesSavedData()
	{
		GsonDataLoader<User> loader = new GsonDataLoader<User>(Robolectric.getShadowApplication().getApplicationContext(), "user", User.class);
//        User testUser = new User("test", "customer", "test@test.test", "Tester Test", "123 Fake Address", "", "Louisville", "KY", "40208", new Date().getTime());
//        loader.saveData(testUser);
//        User savedUser = loader.loadData();
//        assertTrue(testUser.equals(savedUser));
	}
}
