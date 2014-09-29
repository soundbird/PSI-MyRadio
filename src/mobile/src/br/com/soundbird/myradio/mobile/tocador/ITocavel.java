package br.com.soundbird.myradio.mobile.tocador;

import android.net.Uri;

public interface ITocavel{

	public long getId();
	public Uri getLocal();
	public ITocavel getProx();
	
}
