package br.com.soundbird.myradio.mobile.model;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import br.com.soundbird.myradio.mobile.tocador.ITocavel;

public class Lista implements ITocavel{
	
	private static long sCounter = 0;

	private long mId;
	private String mNome;
	private List<Musica> mMusicas;
	private int mIndice;

	public Lista(String nome) {
		this.mId = ++sCounter;
		this.mNome = nome;
		this.mMusicas = new ArrayList<Musica>();
		this.mIndice = 0;
	}
	
	@Override
	public long getId() {
		return mId;
	}
	public String getNome() {
		return mNome;
	}
	public void setNome(String nome) {
		this.mNome = nome;
	}
	public List<Musica> getMusicas() {
		return this.mMusicas;
	}
	
	public void adicionarMusica(Musica musica) {
		mMusicas.add(musica);
	}
	
	public Musica getMusica(int indice) {
		return this.mMusicas.get(indice);
	}

	@Override
	public Uri getLocal() {
		return mMusicas.get(mIndice).getLocal();
	}

	@Override
	public ITocavel getProx() {
		mIndice++;
		
		if (mIndice < mMusicas.size()) {
			return this;
		}
		
		mIndice = 0;
		
		return null;
	}
	
}
