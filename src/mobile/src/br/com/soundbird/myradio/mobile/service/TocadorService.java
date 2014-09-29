package br.com.soundbird.myradio.mobile.service;

import br.com.soundbird.myradio.mobile.tocador.Tocador;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TocadorService extends Service {

	private Tocador mTocador;
	
	@Override
	public void onCreate() {
		
		mTocador = new Tocador();
		
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		
		mTocador.parar();
		mTocador.fechar();
		
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mTocador;
	}

}
