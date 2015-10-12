package com.piquor.app.quiz.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.piquor.app.quiz.model.Category;

public class CategoryDBDataSource extends DataSource<Category>{
	
	public static final String TABLE_CATEGORY = "CATEGORY";

	public static final String COLUMN_LOGO_PATH = "logo_path" ;

	public static final String COLUMN_CATEGORY_NAME = "category_name";

	public static final String COLUMN_CATEGORY_ID = "category_id";

	private String TAG = this.getClass().getName();

	public CategoryDBDataSource(SQLiteDatabase database) {
		super(database);
	}
	
	@Override
	public boolean insert(Category entity) {
		boolean resCode = false;
		try{
				if (entity == null) {
			
					return false;
				}
		long result = mDatabase.insert(TABLE_CATEGORY, null,
				generateContentValuesFromObject(entity));
		resCode =(result != -1);
		}catch(SQLiteConstraintException e){
            Log.e(TAG, "Error in DB transaction "+e);
            resCode = false;
		}catch(SQLException e){
            Log.e(TAG, "Error in DB transaction " + e);
            resCode = false;
		} catch(Exception e){
			Log
					.e(TAG, "Error in DB transaction " + e);
			resCode = false;
		}
		return resCode;
	}

	@Override
	public boolean delete(Category entity) {
		boolean resCode = false;
		try{
				if (entity == null) {
			
					return false;
				}
		int result = mDatabase.delete(TABLE_CATEGORY,
				COLUMN_CATEGORY_ID + " = " + entity.getCategory_id(), null);
		resCode=( result != 0);
		}catch(SQLiteConstraintException e){
            Log.e(TAG, "Error in DB transaction "+e);
            resCode = false;
		}catch(SQLException e){
            Log.e(TAG, "Error in DB transaction " + e);
            resCode = false;
		} catch(Exception e){
			Log.e(TAG, "Error in DB transaction " + e);
			resCode = false;
		}
		return resCode;
	}

	@Override
	public boolean update(Category entity) {
		boolean resCode = false;
		try{
				if (entity == null) {
			
					return false;
				}
			mDatabase.beginTransaction();
				int result = mDatabase.update(TABLE_CATEGORY,generateContentValuesFromObject(entity), COLUMN_CATEGORY_ID+ " = "
						+ entity.getCategory_id(), null);
			mDatabase.setTransactionSuccessful();
			resCode=( result != 0);
		}catch(SQLiteConstraintException e){
            Log.e(TAG, "Error in DB transaction "+e);
            resCode = false;
		}catch(SQLException e){
            Log.e(TAG, "Error in DB transaction "+e);
            resCode = false;
		} catch(Exception e){
			Log.e(TAG, "Error in DB transaction "+e);
			resCode = false;
		}finally {
			mDatabase.endTransaction();
		}
		return resCode;
	}

	@Override
	public List<Category> read() {
		Cursor  cursor = null;
		List<Category> categoryList = new ArrayList<Category>();
		try {
			cursor = mDatabase.query(TABLE_CATEGORY, getAllColumns(), null,
				null, null, null, null);
			categoryList = new ArrayList();
			if (cursor != null && cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					categoryList.add(generateObjectFromCursor(cursor));
				cursor.moveToNext();
				}
			
			}
		}	catch(CursorIndexOutOfBoundsException e) {
            Log.e(TAG, "Error in DB transaction "+e);
			categoryList = null;
			}catch(SQLException e) {
            Log.e(TAG, "Error in DB transaction "+e);
			categoryList = null;
			}catch (Exception e) {	
			Log.e(TAG, "Error in DB transaction "+e);
			categoryList = null;
			} finally {
				if (cursor != null) {
					cursor.close();
					cursor = null;
				}
			}
		return categoryList;
	}

	@Override
	public List<Category> read(String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor  cursor = null;
		List<Category> inventoryProductList = new ArrayList<Category>();
		try {
		 cursor = mDatabase.query(TABLE_CATEGORY, getAllColumns(), selection,selectionArgs, groupBy, having, orderBy);
		System.out.println("####Cursor"+cursor.getCount());
		
		if (cursor != null && cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				inventoryProductList.add(generateObjectFromCursor(cursor));
				cursor.moveToNext();
			}
			
		}
		}	catch(CursorIndexOutOfBoundsException e) {
            Log.e(TAG, "Error in DB transaction "+e);
            inventoryProductList = null;
			}catch(SQLException e) {
            Log.e(TAG, "Error in DB transaction "+e);
            inventoryProductList = null;
			}catch (Exception e) {	
			Log.e(TAG, "Error in DB transaction "+e);
			inventoryProductList = null;
			} finally {
				if (cursor != null) {
					cursor.close();
					cursor = null;
				}
			}
		return inventoryProductList;
	}

	/**
	 * @return
	 */
	private String[] getAllColumns() {
		return new String[] { COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME,COLUMN_LOGO_PATH};
	}

	/**
	 * @param cursor
	 * @return
	 */
private Category generateObjectFromCursor(Cursor cursor) {
		if (cursor == null) {
			return null;
		}
		Category category = new Category();
		category.setCategory_id(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
		category.setCategory_name(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
		category.setLogo_path(cursor.getString(cursor.getColumnIndex(COLUMN_LOGO_PATH)));

		return category;
	}

	/**
	 * @param entity
	 * @return
	 */
	public ContentValues generateContentValuesFromObject(Category entity) {
		if (entity == null) {
			return null;
		}
		ContentValues values = new ContentValues();
		values.put(COLUMN_CATEGORY_NAME, entity.getCategory_name());
		values.put(COLUMN_LOGO_PATH, entity.getLogo_path());
		return values;
	}

	@Override
	public List<Category> readSpecificColumns(String tableName, String[] selectColums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		//Return Specific columns in future but right now not needed
		return null;
	}
}
