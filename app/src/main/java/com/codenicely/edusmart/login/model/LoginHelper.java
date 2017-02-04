package com.codenicely.edusmart.login.model;

import com.codenicely.edusmart.login.LoginCallback;

/**
 * Created by ramya on 12/10/16.
 */

public interface LoginHelper {
    void loginData(String user_id,int login_type, LoginCallback loginCallback);
}
