package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;

@RunWith(RobolectricTestRunner.class)
public class UserLoaderTest 
{
	@Test
	public void testLoadedDataMatchesSavedData()
	{
		GsonDataLoader<User> loader = new GsonDataLoader<User>(Robolectric.getShadowApplication().getApplicationContext(), "user", User.class);
		int size = 2;
		User testUser = new User("test", "pass", "Customer", "test@test.test", "Tester Test", null, null, new String[size], new Phone[size], new Address[size], new Date().getTime(), new Date().getTime(), Integer.valueOf(2), Integer.valueOf(5));
//        User testUser = new User("test", "customer", "test@test.test", "Tester Test", "123 Fake Address", "", "Louisville", "KY", "40208", new Date().getTime());
        loader.saveData(testUser);
        User savedUser = loader.loadData();
        assertTrue(testUser.equals(savedUser));
	}
}
