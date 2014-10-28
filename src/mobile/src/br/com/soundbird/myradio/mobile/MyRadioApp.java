package br.com.soundbird.myradio.mobile;

import br.com.soundbird.myradio.mobile.infra.MyRadioDbHelper;
import br.com.soundbird.myradio.mobile.model.Musica;
import br.com.soundbird.myradio.mobile.model.MyRadioCache;
import android.app.Application;
import android.content.Context;

public class MyRadioApp extends Application {

	private static Context sContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		MyRadioApp.sContext = getApplicationContext();
		
		MyRadioDbHelper dbHelper = new MyRadioDbHelper(sContext);
		
		for (Musica musica : dbHelper.musicaDao.query()) {
			MyRadioCache.lista.adicionarMusica(musica);
		}
		
		dbHelper.close();
	}
	
	public static Context getContext() {
		return sContext;
	}
}
