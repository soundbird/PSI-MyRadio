package br.com.soundbird.myradio.mobile.model;

import br.com.soundbird.myradio.mobile.tocador.ITocavel;
import android.net.Uri;

public class Musica implements ITocavel {
	
	// ITocavel
	private long mId;
	private String mNome;
	private Uri mLocal;
	
	// Musica
	private String mArtista;
	private String mAlbum;
	private String mEstilo;
	private int mAno;
	private int mDuracao;
	
	public Musica(long id) {
		this.mId = id;
	}

	@Override
	public ITocavel getProx() {
		return null;
	}
	
	@Override
	public String toString() {
		return this.mNome;
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
	@Override
	public Uri getLocal() {
		return mLocal;
	}
	public void setLocal(Uri local) {
		this.mLocal = local;
	}
	public String getArtista() {
		return mArtista;
	}
	public void setArtista(String mArtista) {
		this.mArtista = mArtista;
	}
	public String getAlbum() {
		return mAlbum;
	}
	public void setAlbum(String mAlbum) {
		this.mAlbum = mAlbum;
	}
	public String getEstilo() {
		return mEstilo;
	}
	public void setEstilo(String mEstilo) {
		this.mEstilo = mEstilo;
	}
	public int getAno() {
		return mAno;
	}
	public void setAno(int mAno) {
		this.mAno = mAno;
	}
	public int getDuracao() {
		return mDuracao;
	}
	public void setDuracao(int mDuracao) {
		this.mDuracao = mDuracao;
	}
}
