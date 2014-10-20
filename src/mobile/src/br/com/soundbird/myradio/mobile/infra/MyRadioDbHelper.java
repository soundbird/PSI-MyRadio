package br.com.soundbird.myradio.mobile.infra;

import br.com.soundbird.myradio.mobile.model.dao.MusicaDao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyRadioDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MyRadio.db";
	
	public final MusicaDao musicaDao;

	public MyRadioDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		musicaDao = new MusicaDao(this);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		musicaDao.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		musicaDao.onUpgrade(db, oldVersion, newVersion);
	}

}
