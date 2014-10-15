package br.com.soundbird.myradio.mobile;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;
import br.com.soundbird.myradio.mobile.model.MyRadioCache;
import br.com.soundbird.myradio.mobile.service.TocadorService;
import br.com.soundbird.myradio.mobile.tocador.ITocador;
import br.com.soundbird.myradio.mobile.tocador.ITocavel;
import br.com.soundbird.myradio.mobile.tocador.OnStateChangedListener;

public class PlayerFragment extends Fragment implements ServiceConnection {
	
	public static final String ARG_TOCAR = "tocar";
	
	private SeekBar mBarraTocando;
	private ToggleButton mTocarPausar;
	private ITocavel mTocavel;
	private ITocador mTocador;
	
	public PlayerFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mTocavel = MyRadioCache.tocavel;
		
		getActivity().bindService(new Intent(getActivity(), TocadorService.class), this, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_player,
				container, false);
		
		mBarraTocando = (SeekBar) rootView.findViewById(R.id.barra_tocando);
		
		mTocarPausar = (ToggleButton) rootView.findViewById(R.id.botao_tocar_pausar);
		
		mBarraTocando.setOnSeekBarChangeListener(new BarraTocandoListener());
		mTocarPausar.setOnCheckedChangeListener(new BotaoTocarListener());
		
		return rootView;
	}
	
	@Override
	public void onDestroyView() {
		getActivity().unbindService(this);
		super.onDestroyView();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mTocador = (ITocador) service;
		
		mTocador.setOnStateChangedListener(new EstadoAtualizado());
		
		mTocarPausar.setChecked(true);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		mTocador = null;
	}
	
	private class BarraTocandoListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (fromUser) {
				mTocador.pular(progress);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
	}
	
	private class BotaoTocarListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			
			Log.i("MyRadio", "isChecked: " + isChecked);
			if (isChecked) {
				mTocador.tocar(mTocavel);
			} else {
				mTocador.pausar();
			}
		}
		
	}
	
	private class EstadoAtualizado implements OnStateChangedListener {

		@Override
		public void onPaused() {
			mTocarPausar.setChecked(false);
		}

		@Override
		public void onStarted() {
			mTocarPausar.setChecked(true);
		}

		@Override
		public void onInfoChanged(int duration) {
			mBarraTocando.setMax(duration);
		}

		@Override
		public void onProgressChanged(int progress) {
			mBarraTocando.setProgress(progress);
		}
		
	}

}
