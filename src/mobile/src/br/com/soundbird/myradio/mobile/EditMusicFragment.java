package br.com.soundbird.myradio.mobile;

import br.com.soundbird.myradio.mobile.model.Musica;
import br.com.soundbird.myradio.mobile.model.MyRadioCache;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditMusicFragment extends Fragment {

	public static final String ARG_ITEM_ID = "item_id";

	private Musica mMusica;
	
	private EditText mNome;
	private EditText mArtista;
	private EditText mAlbum;
	private EditText mAno;
	private EditText mEstilo;
	private EditText mDuracao;
	
	public EditMusicFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mMusica = MyRadioCache.lista.getMusica(getArguments().getInt(
					ARG_ITEM_ID));
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_music,
				container, false);
		
		mNome = (EditText) rootView.findViewById(R.id.edit_nome);
		mArtista = (EditText) rootView.findViewById(R.id.edit_artista);
		
		return rootView;
	}
	
}
