package com.codenicely.edusmart.login.view;

import com.codenicely.edusmart.login.data.LoginDataResponse;

/**
 * Created by ramya on 11/10/16.
 */

public interface LoginView {
    void showProgressBar(boolean show);
    void showLoginStatus(LoginDataResponse loginDataResponse);
    void showError(String message);

}
