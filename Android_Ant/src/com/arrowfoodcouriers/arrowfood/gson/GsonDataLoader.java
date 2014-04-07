package com.arrowfoodcouriers.arrowfood.gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import android.content.Context;

import com.arrowfoodcouriers.arrowfood.DrawerListObject;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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
	
	public T loadAssetData() {
		String json;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(DrawerListObject.class, new DrawerListObjectDeserializer());
		Gson gson = gsonBuilder.create();
		
		try {
			InputStream inputStream = context.getAssets().open(fileName);
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
	
	private class DrawerListObjectDeserializer implements JsonDeserializer<DrawerListObject> {

		@Override
		public DrawerListObject deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			
			JsonObject jobject = (JsonObject) json;
			
			DrawerListObject obj = new DrawerListObject(jobject.get("title").getAsString(),
					jobject.get("position").getAsInt(),
					GsonDataLoader.this.context.getResources().getIdentifier(jobject.get("image").getAsString(), "drawable", GsonDataLoader.this.context.getPackageName()),
					jobject.get("logoutOnly").getAsBoolean(),
					jobject.get("loginOnly").getAsBoolean(),
					jobject.get("isHeader").getAsBoolean());
			
			return obj;
		}
		
	}
}
