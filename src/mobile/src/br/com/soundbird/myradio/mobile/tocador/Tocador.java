package br.com.soundbird.myradio.mobile.tocador;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.util.Log;
import br.com.soundbird.myradio.mobile.MyRadioApp;

public class Tocador extends Binder implements ITocador, OnCompletionListener {

	private final MediaPlayer mMediaPlayer = new MediaPlayer();
	
	private ITocavel mTocando;
	
	private OnPausedListener mOnPausedListener; 
	
	public Tocador() {
		mMediaPlayer.setOnCompletionListener(this);
	}
	
	@Override
	public void tocar() {
		mMediaPlayer.start();
	}

	@Override
	public void tocar(ITocavel tocavel) {
		if (!tocando(tocavel)) {
			pausar();
			mOnPausedListener = null;
			
			mMediaPlayer.reset();
			try {
				mMediaPlayer.setDataSource(MyRadioApp.getContext(), tocavel.getLocal());
			} catch (Exception e) {
				Log.d("MyRadio", "Erro ao definir fonte de dados", e);
			}
			try {
				mMediaPlayer.prepare();
			} catch (Exception e) {
				Log.d("MyRadio", "Erro ao preparar", e);
			}
			mTocando = tocavel;
		}
		
		tocar();
	}

	@Override
	public void pausar() {
		mMediaPlayer.pause();
		
		onPaused();
	}

	@Override
	public void parar() {
		mMediaPlayer.stop();
	}

	@Override
	public boolean tocando(ITocavel tocavel) {
		return mTocando != null && mTocando.getClass() == tocavel.getClass() && mTocando.getId() == tocavel.getId();
	}
	
	public void fechar() {
		parar();
		mMediaPlayer.release();
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		mTocando = mTocando.getProx();
		
		if (mTocando != null) {
			tocar(mTocando);
		}
		
		onPaused();
	}
	
	private void onPaused() {
		if (mOnPausedListener != null) {
			mOnPausedListener.onPaused();
		}
	}

	@Override
	public void setOnPausedListener(
			OnPausedListener onPausedListener) {
		this.mOnPausedListener = onPausedListener;
	}

}
