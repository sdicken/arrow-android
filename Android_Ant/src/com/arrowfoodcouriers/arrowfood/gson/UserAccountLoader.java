package com.arrowfoodcouriers.arrowfood.gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;

import com.arrowfoodcouriers.arrowfood.Models.User;
import com.google.gson.Gson;

public class UserAccountLoader {
	private static final String FILE_NAME = "user";
	private Context context;
	
	public UserAccountLoader(Context context) {
		this.context = context;
	}
	
	public void saveData(User userData) {
		FileOutputStream outputStream;
		
		// Convert to JSON object
		Gson gson = new Gson();
		String s = gson.toJson(userData);
		
		try {
			outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			outputStream.write(s.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveData(String userJson) {
		FileOutputStream outputStream;
		
		try {
			outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			outputStream.write(userJson.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public User loadData() {
		String json;
		Gson gson = new Gson();
		try {
			FileInputStream inputStream = context.openFileInput(FILE_NAME);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			
			json = sb.toString();
			bufferedReader.close();
			return gson.fromJson(json, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
