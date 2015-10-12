package com.piquor.app.quiz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtils {

	public static final String QUIZ_SP = "QUIZ_SP";
	public static final String SCORE = "SCORE";

	public static int getScore(Context context) {
		SharedPreferences pref = context.getSharedPreferences(QUIZ_SP, Context.MODE_PRIVATE);
		return pref.getInt(SCORE, 0);
	}

	public static void setScore(Context context,int score) {
		SharedPreferences pref = context.getSharedPreferences(QUIZ_SP, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(SCORE, score);
		editor.commit();
	}

}
