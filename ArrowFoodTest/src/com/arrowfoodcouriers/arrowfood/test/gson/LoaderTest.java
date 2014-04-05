package com.arrowfoodcouriers.arrowfood.test.gson;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.gson.UserAccountLoader;

@RunWith(RobolectricTestRunner.class)
public class LoaderTest 
{
	@Test
	public void testLoadedDataMatchesSavedData()
	{
		UserAccountLoader loader = new UserAccountLoader(Robolectric.getShadowApplication().getApplicationContext());
        User testUser = new User("test", "customer", "test@test.test", "Tester Test", "123 Fake Address", "", "Louisville", "KY", "40208", new Date().getTime());
        loader.saveData(testUser);
        User savedUser = loader.loadData();
        assertTrue(testUser.equals(savedUser));
	}
}
