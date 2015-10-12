package com.piquor.app.quiz.utils;

public class ValidationUtil {

	public static Boolean isNotEmpty(String s) {
		if (s != null && !s.trim().equals("")) {
				return true;
		}
		return false;
	}


}
