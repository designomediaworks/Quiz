package com.piquor.app.quiz.database;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public abstract class DataSource<T> {

	protected SQLiteDatabase mDatabase;
	/**
	 * @param database
	 */
	public DataSource(SQLiteDatabase database) {
		mDatabase = database;
	}
	/**
	 * @param entity
	 * @return
	 */
	public abstract boolean insert(T entity);
	/**
	 * @param entity
	 * @return
	 */
	public abstract boolean delete(T entity);
	/**
	 * @param entity
	 * @return
	 */
	public abstract boolean update(T entity);
	/**
	 * @return
	 */
	public abstract List<T> read();
	/**
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public abstract List<T> read(String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy);
	/**
	 * @param tableName
	 * @param selectColums
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public abstract List<T> readSpecificColumns(String tableName ,String[] selectColums,String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy);
	

}
