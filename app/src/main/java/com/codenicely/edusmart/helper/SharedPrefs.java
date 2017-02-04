package com.codenicely.edusmart.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by ramya on 3/2/17.
 */

public class SharedPrefs {
    // Shared preferences file name
    private static final String PREF_NAME = "welcome";
    public static final String KEY_LOGIN_TYPE="loginType";
    private static final String KEY_IS_STUDENT_LOGGEDIN = "isStudentLoggedIn";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_TEACHER_LOGGED_IN="isTeacherLoggedIn";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_HELPLINE_MOBILE = "helpline_mobile";

    private static final String KEY_ACCESS_TOKEN = "access_token";


    // LogCat tag
    private static String TAG = "Shared Preference";

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public  void setTeacherLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_TEACHER_LOGGED_IN,isLoggedIn);
        editor.commit();
        Log.d(TAG,"User login seesiom modified");
    }
    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_STUDENT_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_STUDENT_LOGGEDIN, false);
    }
    public boolean isTeacherLoggedIn()
    {
        return pref.getBoolean(KEY_IS_TEACHER_LOGGED_IN,false);
    }

    public void setEmailId(String emailId) {

        editor.putString(KEY_EMAIL, emailId);
        editor.commit();

    }

    public String getUserName() {

        return pref.getString(KEY_USER_NAME, "NA");

    }

    public void setUserName(String userName) {

        editor.putString(KEY_USER_NAME, userName);
        editor.commit();

    }


    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, "NoAccessToken");
    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();

    }
    public void setLoginType(int login_type){
        editor.putInt(KEY_LOGIN_TYPE,login_type);
        editor.commit();
    }
    public int getLoginType()
    {
        return pref.getInt(KEY_LOGIN_TYPE,0);
    }


    public String getHelplineNumber() {

        return pref.getString(KEY_HELPLINE_MOBILE, "9549089999");
    }

    public void setHelplineNumber(String helpline_number) {
        editor.putString(KEY_HELPLINE_MOBILE, helpline_number);

        editor.commit();

    }

    public String getEmail() {

        return pref.getString(KEY_EMAIL, "Not Available");

    }
}
