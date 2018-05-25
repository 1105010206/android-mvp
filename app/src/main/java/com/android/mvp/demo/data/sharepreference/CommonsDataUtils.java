package com.android.mvp.demo.data.sharepreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;


import com.android.mvp.demo.GalbsApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonsDataUtils {

	private static CommonsDataUtils commonsDataUtils;

	SharedPreferences spf = GalbsApplication.getAppPrefrences();

	Editor editor = spf.edit();

	private CommonsDataUtils() {
	}

	public static final String CURRENT_USER_NAME = "current_user_name";

	public static final String CURRENT_USER_PASSWORD = "current_user_password";

	public static synchronized CommonsDataUtils getInstances() {
		if (commonsDataUtils == null) {
			commonsDataUtils = new CommonsDataUtils();
		}
		return commonsDataUtils;
	}



	public String getUserName() {
		return spf.getString(CURRENT_USER_NAME, null);
	}

	public void putUserName(String userName) {
		editor.putString(CURRENT_USER_NAME, userName).commit();
	}

	public String getPassword() {
		return spf.getString(CURRENT_USER_PASSWORD, null);
	}

	public void putPassword(String password) {
		editor.putString(CURRENT_USER_PASSWORD, password).commit();
	}













}
