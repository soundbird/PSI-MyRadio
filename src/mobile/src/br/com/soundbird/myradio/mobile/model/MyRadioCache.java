package br.com.soundbird.myradio.mobile.model;

import br.com.soundbird.myradio.mobile.tocador.ITocavel;

public class MyRadioCache {

	public static Lista lista;
	public static Musica musica;
	public static ITocavel tocavel;
	
	static {
		lista = new Lista("Padrão");
	}
}
