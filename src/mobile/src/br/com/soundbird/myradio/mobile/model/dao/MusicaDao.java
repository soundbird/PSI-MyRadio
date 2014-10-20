package br.com.soundbird.myradio.mobile.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.soundbird.myradio.mobile.R;
import br.com.soundbird.myradio.mobile.infra.MyRadioDbHelper;
import br.com.soundbird.myradio.mobile.model.Musica;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MusicaDao implements IDao<Musica> {

	public static final String TABLE_NAME = "musica";
	public static final String COLUMN_NAME_MUSICA_ID = "musicaid";
	public static final String COLUMN_NAME_NOME = "nome";
	public static final String COLUMN_NAME_LOCAL = "local";
	public static final String COLUMN_NAME_ARTISTA = "artista";
	public static final String COLUMN_NAME_ALBUM = "album";
	public static final String COLUMN_NAME_ESTILO = "estilo";
	public static final String COLUMN_NAME_ANO = "ano";
	public static final String COLUMN_NAME_DURACAO = "duracao";
	
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + TABLE_NAME + " (" +
			COLUMN_NAME_MUSICA_ID	+ INTEGER_TYPE	+ " PRIMARY KEY" + COMMA_SEP +
			COLUMN_NAME_NOME		+ TEXT_TYPE		+ COMMA_SEP +
			COLUMN_NAME_LOCAL		+ TEXT_TYPE		+ COMMA_SEP +
			COLUMN_NAME_ARTISTA		+ TEXT_TYPE		+ COMMA_SEP +
			COLUMN_NAME_ALBUM		+ TEXT_TYPE		+ COMMA_SEP +
			COLUMN_NAME_ESTILO		+ TEXT_TYPE		+ COMMA_SEP +
			COLUMN_NAME_ANO			+ INTEGER_TYPE	+ COMMA_SEP +
			COLUMN_NAME_DURACAO		+ INTEGER_TYPE	+
			");";
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	
	private final MyRadioDbHelper mDbHelper;
	
	public MusicaDao(MyRadioDbHelper dbHelper) {
		mDbHelper = dbHelper;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
		
		ContentValues musica1 = new ContentValues();
		ContentValues musica2 = new ContentValues();
		
		musica1.put(COLUMN_NAME_NOME, "Some Sort of Creature");
		musica1.put(COLUMN_NAME_LOCAL, "android.resource://br.com.soundbird.myradio.mobile/" + R.raw.some_sort_of_creature);

		musica2.put(COLUMN_NAME_NOME, "Lumaban Ka");
		musica2.put(COLUMN_NAME_LOCAL, "android.resource://br.com.soundbird.myradio.mobile/" + R.raw.lumaban_ka);
		
		db.insert(TABLE_NAME, null, musica1);
		db.insert(TABLE_NAME, null, musica2);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	@Override
	public void insert(Musica entidade) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		ContentValues valores = new ContentValues();
		valores.put(COLUMN_NAME_NOME, entidade.getNome());
		valores.put(COLUMN_NAME_LOCAL, entidade.getLocal().toString());
		valores.put(COLUMN_NAME_ARTISTA, entidade.getArtista());
		valores.put(COLUMN_NAME_ALBUM, entidade.getAlbum());
		valores.put(COLUMN_NAME_ESTILO, entidade.getEstilo());
		valores.put(COLUMN_NAME_ANO, entidade.getAno());
		valores.put(COLUMN_NAME_DURACAO, entidade.getDuracao());
		
		db.insert(TABLE_NAME, null, valores);
		db.close();
	}

	@Override
	public void update(Musica entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Musica entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Musica find(int id) {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		String[] colunas = {
			COLUMN_NAME_MUSICA_ID,
			COLUMN_NAME_NOME,
			COLUMN_NAME_LOCAL,
			COLUMN_NAME_ARTISTA,
			COLUMN_NAME_ALBUM,
			COLUMN_NAME_ESTILO,
			COLUMN_NAME_ANO,
			COLUMN_NAME_DURACAO
		};
		String whereClause = COLUMN_NAME_MUSICA_ID + " = ?";
		String[] whereValues = {
			String.valueOf(id)
		};
		
		Cursor result = db.query(TABLE_NAME, colunas, whereClause, whereValues, null, null, null);
		
		if (result == null) {
			return null;
		}
		
		result.moveToFirst();
		
		Musica musica = new Musica(result.getLong(0));
		musica.setNome(result.getString(1));
		musica.setLocal(Uri.parse(result.getString(2)));
		musica.setArtista(result.getString(3));
		musica.setAlbum(result.getString(4));
		musica.setEstilo(result.getString(5));
		musica.setAno(result.getInt(6));
		musica.setDuracao(result.getInt(7));
		
		return musica;
	}

	@Override
	public List<Musica> query() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		String[] colunas = {
			COLUMN_NAME_MUSICA_ID,
			COLUMN_NAME_NOME,
			COLUMN_NAME_LOCAL,
			COLUMN_NAME_ARTISTA,
			COLUMN_NAME_ALBUM,
			COLUMN_NAME_ESTILO,
			COLUMN_NAME_ANO,
			COLUMN_NAME_DURACAO
		};
		
		Cursor result = db.query(TABLE_NAME, colunas, null, null, null, null, null);
		
		if (result == null) {
			return null;
		}
		
		List<Musica> musicas = new ArrayList<Musica>();
		
		while (result.moveToNext()) {
			Musica musica = new Musica(result.getLong(0));
			musica.setNome(result.getString(1));
			musica.setLocal(Uri.parse(result.getString(2)));
			musica.setArtista(result.getString(3));
			musica.setAlbum(result.getString(4));
			musica.setEstilo(result.getString(5));
			musica.setAno(result.getInt(6));
			musica.setDuracao(result.getInt(7));
			
			musicas.add(musica);
		}
		
		return musicas;
	}
	
}
