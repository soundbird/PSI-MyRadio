package br.com.soundbird.myradio.mobile.model;

import android.net.Uri;
import br.com.soundbird.myradio.mobile.R;

public class MyRadioCache {

	public static Lista lista;
	public static Musica musica;
	
	static {
		lista = new Lista("Padrão");
		lista.adicionarMusica(new Musica("Some Sort of Creature", Uri.parse("android.resource://br.com.soundbird.myradio.mobile/" + R.raw.some_sort_of_creature)));
		lista.adicionarMusica(new Musica("Lumaban Ka", Uri.parse("android.resource://br.com.soundbird.myradio.mobile/" + R.raw.lumaban_ka)));
	}
}
