package com.codenicely.edusmart.login.view;

import com.codenicely.edusmart.login.model.data.LoginDataResponse;

/**
 * Created by ramya on 11/10/16.
 */

public interface LoginView {
    void showProgressBar(boolean show);

    void onLoginSuccess(LoginDataResponse loginDataResponse);

    void showError(String message);
}
