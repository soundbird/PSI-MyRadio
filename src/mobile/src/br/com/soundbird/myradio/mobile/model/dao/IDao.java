package br.com.soundbird.myradio.mobile.model.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public interface IDao<T> {

	public void onCreate(SQLiteDatabase db);
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	public void insert(T entidade);
	public void update(T entidade);
	public void delete(T entidade);
	public T find(int id);
	public List<T> query();
	
}
