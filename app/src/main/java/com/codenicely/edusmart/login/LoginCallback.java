package com.codenicely.edusmart.login;

import com.codenicely.edusmart.login.model.data.LoginDataResponse;


/**
 * Created by ramya on 12/10/16.
 */

public interface LoginCallback {

void onLoginSuccess(LoginDataResponse loginResponse);
    void onLoginFailure(String error);
}
