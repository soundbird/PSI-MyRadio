package br.com.soundbird.myradio.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;
import br.com.soundbird.myradio.mobile.model.Musica;
import br.com.soundbird.myradio.mobile.model.MyRadioCache;

/**
 * A fragment representing a single Music detail screen. This fragment is either
 * contained in a {@link MusicListActivity} in two-pane mode (on tablets) or a
 * {@link MusicDetailActivity} on handsets.
 */
public class MusicDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	private Musica mMusica;
	
	private ToggleButton mTocarPausar;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MusicDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mMusica = MyRadioCache.lista.getMusica(getArguments().getInt(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_music_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mMusica != null) {
			((TextView) rootView.findViewById(R.id.music_detail))
					.setText(mMusica.getNome());
		}
		
		mTocarPausar = (ToggleButton) rootView.findViewById(R.id.botao_tocar);
		
		mTocarPausar
			.setOnCheckedChangeListener(new BotaoTocarListener());

		return rootView;
	}
	
	private class BotaoTocarListener implements OnCheckedChangeListener {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			if (isChecked) {
				MyRadioCache.tocavel = mMusica;
				Intent playerIntent = new Intent(getActivity(), PlayerActivity.class);
				startActivity(playerIntent);
			} else {
			}

		}
	}
}
