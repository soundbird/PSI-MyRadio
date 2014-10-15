package br.com.soundbird.myradio.mobile.tocador;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Handler;
import android.util.Log;
import br.com.soundbird.myradio.mobile.MyRadioApp;

public class Tocador extends Binder implements ITocador, OnCompletionListener {

	public static final long PROGRESS_DELAY = 200;

	private final MediaPlayer mMediaPlayer = new MediaPlayer();
	
	private ITocavel mTocando;
	
	private OnStateChangedListener mOnStateChangedListener;
	
	private final Handler mProgressHandler = new Handler();
	
	private final Runnable mProgress = new ProgressUpdate();
	
	public Tocador() {
		mMediaPlayer.setOnCompletionListener(this);
	}
	
	@Override
	public void tocar() {
		mMediaPlayer.start();
		
		mProgressHandler.post(mProgress);
		
		onStarted();
	}

	@Override
	public void tocar(ITocavel tocavel) {
		if (!tocavelCarregado(tocavel)) {
			pausar();
			
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
			onInfoChanged();
			mTocando = tocavel;
		}
		
		tocar();
	}

	@Override
	public void pausar() {
		mMediaPlayer.pause();
		
		mProgressHandler.removeCallbacks(mProgress);
		
		onPaused();
		onProgressChanged();
	}

	@Override
	public void parar() {
		mMediaPlayer.stop();
	}

	@Override
	public void pular(int posicao) {
		mMediaPlayer.seekTo(posicao);
		
		onProgressChanged();
	}

	@Override
	public boolean tocando(ITocavel tocavel) {
		return mMediaPlayer.isPlaying() && tocavelCarregado(tocavel);
	}
	
	private boolean tocavelCarregado(ITocavel tocavel) {
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
		} else {
			pausar();
		}
	}
	
	private void onPaused() {
		if (mOnStateChangedListener != null) {
			mOnStateChangedListener.onPaused();
		}
	}
	
	private void onStarted() {
		if (mOnStateChangedListener != null) {
			mOnStateChangedListener.onStarted();
		}
	}
	
	private void onInfoChanged() {
		if (mOnStateChangedListener != null) {
			mOnStateChangedListener.onInfoChanged(mMediaPlayer.getDuration());
		}
	}
	
	private void onProgressChanged() {
		if (mOnStateChangedListener != null) {
			mOnStateChangedListener.onProgressChanged(mMediaPlayer.getCurrentPosition());
		}
	}

	@Override
	public void setOnStateChangedListener(
			OnStateChangedListener onStateChangedListener) {
		this.mOnStateChangedListener = onStateChangedListener;
	}
	
	private class ProgressUpdate implements Runnable {

		@Override
		public void run() {
			onProgressChanged();
			mProgressHandler.postDelayed(mProgress, PROGRESS_DELAY);
		}
		
	}

}
