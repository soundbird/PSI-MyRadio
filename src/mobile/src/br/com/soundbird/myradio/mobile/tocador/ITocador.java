package br.com.soundbird.myradio.mobile.tocador;



public interface ITocador {
	public void tocar();
	public void tocar(ITocavel tocavel);
	public void pausar();
	public void parar();
	public boolean tocando(ITocavel tocavel);
	public void setOnPausedListener(OnPausedListener onEndedListener);
}
