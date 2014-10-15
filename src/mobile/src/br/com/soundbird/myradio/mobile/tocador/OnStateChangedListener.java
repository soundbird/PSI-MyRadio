package br.com.soundbird.myradio.mobile.tocador;

public interface OnStateChangedListener {

	public void onStarted();
	public void onPaused();
	public void onInfoChanged(int duration);
	public void onProgressChanged(int progress);
	
}
