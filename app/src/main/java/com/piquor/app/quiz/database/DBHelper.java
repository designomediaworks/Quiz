package com.piquor.app.quiz.database;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import com.piquor.app.quiz.utils.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A helper class to manage database creation and version management using an
 * application's raw asset files.
 *
 */
public final class DBHelper extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "quiz.db";
	private static final int DATABASE_VERSION = 1;
	private static DBHelper dbHelper = null;

	private DBHelper() {
		super(null, null, null, 1);
	}

	/**
	 * @param context
	 * @return
	 */
	public static synchronized DBHelper getInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
		}
		return dbHelper;
	}

	/**
	 * @param context
	 */
	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//super(context, DATABASE_NAME, Environment.getExternalStorageDirectory().getAbsolutePath(), null, DATABASE_VERSION);
	}


}
