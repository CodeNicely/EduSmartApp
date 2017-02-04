package com.codenicely.edusmart.login.presenter;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.login.LoginCallback;
import com.codenicely.edusmart.login.model.data.LoginDataResponse;
import com.codenicely.edusmart.login.model.LoginHelper;
import com.codenicely.edusmart.login.view.LoginView;

/**
 * Created by ramya on 12/10/16.
 */

public class LoginPresenterImp implements LoginPresenter {
    private LoginHelper loginHelper;
    private LoginView login;

    public LoginPresenterImp(LoginView login, LoginHelper loginHelper) {
        this.login = login;
        this.loginHelper = loginHelper;
    }


    @Override
    public void getLoginData(String user_id,int login_type) {
        login.showProgressBar(true);
        loginHelper.loginData(user_id,login_type, new LoginCallback() {
            @Override
            public void onLoginSuccess(LoginDataResponse loginResponse) {
                if(loginResponse.isSuccess()) {
                    login.onLoginSuccess(loginResponse);
                }
                else {
                    login.showError(loginResponse.getMessage());
                }
                login.showProgressBar(false);
            }

            @Override
            public void onLoginFailure(String error) {
                login.showError(MyApplication.getContext().getResources().getString(R.string.failure_message));
                login.showProgressBar(false);
            }
        });
    }
}
