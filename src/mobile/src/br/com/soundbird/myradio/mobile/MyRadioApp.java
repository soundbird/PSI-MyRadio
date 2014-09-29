package br.com.soundbird.myradio.mobile;

import android.app.Application;
import android.content.Context;

public class MyRadioApp extends Application {

	private static Context sContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		MyRadioApp.sContext = getApplicationContext();
	}
	
	public static Context getContext() {
		return sContext;
	}
}
