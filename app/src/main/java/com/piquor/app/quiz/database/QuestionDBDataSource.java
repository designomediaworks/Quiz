package com.piquor.app.quiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.piquor.app.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBDataSource extends DataSource<Question>{

	public static final String TABLE_QUESTION = "QUESTION";

	public static final String COLUMN_QUESTION_ID = "question_id" ;

	public static final String COLUMN_CATEGORY_ID = "category_id";

	public static final String COLUMN_QUESTION_TEXT = "question_text";

	public static final String COLUMN_OPTION_A= "option_a";

	public static final String COLUMN_OPTION_B= "option_b";

	public static final String COLUMN_OPTION_C= "option_c";

	public static final String COLUMN_OPTION_D= "option_d";

	public static final String COLUMN_ANSWER= "answer";

	private String TAG = this.getClass().getName();

	public QuestionDBDataSource(SQLiteDatabase database) {
		super(database);
	}

	@Override
	public boolean insert(Question entity) {
		boolean resCode = false;
		try{
			if (entity == null) {

				return false;
			}
			mDatabase.beginTransaction();
			long result = mDatabase.insert(TABLE_QUESTION, null,
					generateContentValuesFromObject(entity));
			mDatabase.setTransactionSuccessful();
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
		}finally {
			mDatabase.endTransaction();
		}
		return resCode;
	}

	@Override
	public boolean delete(Question entity) {
		boolean resCode = false;
		try{
			if (entity == null) {

				return false;
			}
			mDatabase.beginTransaction();
			int result = mDatabase.delete(TABLE_QUESTION,
					COLUMN_QUESTION_ID + " = " + entity.getQuestion_id(), null);
			mDatabase.setTransactionSuccessful();;
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
		}finally {
			mDatabase.endTransaction();
		}
		return resCode;
	}

	@Override
	public boolean update(Question entity) {
		boolean resCode = false;
		try{
			if (entity == null) {

				return false;
			}
			int result = mDatabase.update(TABLE_QUESTION,generateContentValuesFromObject(entity), COLUMN_QUESTION_ID+ " = "
					+ entity.getQuestion_id(), null);
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
		}
		return resCode;
	}

	@Override
	public List<Question> read() {
		Cursor  cursor = null;
		List<Question> QuestionList = new ArrayList<Question>();
		try {
			cursor = mDatabase.query(TABLE_QUESTION, getAllColumns(), null,
					null, null, null, null);
			QuestionList = new ArrayList();
			if (cursor != null && cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					QuestionList.add(generateObjectFromCursor(cursor));
					cursor.moveToNext();
				}

			}
		}	catch(CursorIndexOutOfBoundsException e) {
			Log.e(TAG, "Error in DB transaction "+e);
			QuestionList = null;
		}catch(SQLException e) {
			Log.e(TAG, "Error in DB transaction "+e);
			QuestionList = null;
		}catch (Exception e) {
			Log.e(TAG, "Error in DB transaction "+e);
			QuestionList = null;
		} finally {
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
		}
		return QuestionList;
	}

	@Override
	public List<Question> read(String selection, String[] selectionArgs,
							   String groupBy, String having, String orderBy) {
		Cursor  cursor = null;
		List<Question> questionList = new ArrayList<Question>();
		try {
			cursor = mDatabase.query(TABLE_QUESTION, getAllColumns(), selection,selectionArgs, groupBy, having, orderBy);
			System.out.println("####Cursor"+cursor.getCount());

			if (cursor != null && cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					questionList.add(generateObjectFromCursor(cursor));
					cursor.moveToNext();
				}

			}
		}	catch(CursorIndexOutOfBoundsException e) {
			Log.e(TAG, "Error in DB transaction "+e);
			questionList = null;
		}catch(SQLException e) {
			Log.e(TAG, "Error in DB transaction "+e);
			questionList = null;
		}catch (Exception e) {
			Log.e(TAG, "Error in DB transaction "+e);
			questionList = null;
		} finally {
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
		}
		return questionList;
	}

	/**
	 * @return
	 */
	private String[] getAllColumns() {
		return new String[] { COLUMN_QUESTION_ID, COLUMN_CATEGORY_ID,COLUMN_QUESTION_TEXT,COLUMN_OPTION_A,COLUMN_OPTION_B,COLUMN_OPTION_C,COLUMN_OPTION_D,COLUMN_ANSWER};
	}

	/**
	 * @param cursor
	 * @return
	 */
	private Question generateObjectFromCursor(Cursor cursor) {
		if (cursor == null) {
			return null;
		}
		Question Question = new Question();
		Question.setQuestion_id(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ID)));
		Question.setCategory_id(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
		Question.setQuestion_text(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_TEXT)));
		Question.setOption_a(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_A)));
		Question.setOption_b(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_B)));
		Question.setOption_c(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_C)));
		Question.setOption_d(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_D)));
		Question.setAnswer(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER)));

		return Question;
	}

	/**
	 * @param entity
	 * @return
	 */
	public ContentValues generateContentValuesFromObject(Question entity) {
		if (entity == null) {
			return null;
		}
		ContentValues values = new ContentValues();
		values.put(COLUMN_CATEGORY_ID, entity.getCategory_id());
		values.put(COLUMN_QUESTION_TEXT, entity.getQuestion_text());
		values.put(COLUMN_OPTION_A, entity.getOption_a());
		values.put(COLUMN_OPTION_B, entity.getOption_b());
		values.put(COLUMN_OPTION_C, entity.getOption_c());
		values.put(COLUMN_OPTION_D, entity.getOption_d());
		values.put(COLUMN_ANSWER, entity.getAnswer());
		return values;
	}

	@Override
	public List<Question> readSpecificColumns(String tableName, String[] selectColums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		//Return Specific columns in future but right now not needed
		return null;
	}
}
