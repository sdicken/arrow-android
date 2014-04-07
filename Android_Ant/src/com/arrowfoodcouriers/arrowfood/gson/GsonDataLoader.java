package com.arrowfoodcouriers.arrowfood.gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;

import com.arrowfoodcouriers.arrowfood.Models.User;
import com.google.gson.Gson;

public class GsonDataLoader<T> {
	private final String fileName;
	private Context context;
	private final Class<T> typeParameterClass;
	
	public GsonDataLoader(Context context, String fileName, Class<T> typeParameterClass) {
		this.context = context;
		this.fileName = fileName;
		this.typeParameterClass = typeParameterClass;
	}
	
	public void saveData(T data) {
		FileOutputStream outputStream;
		
		// Convert to JSON object
		Gson gson = new Gson();
		String s = gson.toJson(data);
		
		try {
			outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outputStream.write(s.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveData(String jsonString) {
		FileOutputStream outputStream;
		
		try {
			outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outputStream.write(jsonString.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public T loadData() {
		String json;
		Gson gson = new Gson();
		try {
			FileInputStream inputStream = context.openFileInput(fileName);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			
			json = sb.toString();
			bufferedReader.close();
			return gson.fromJson(json, typeParameterClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
