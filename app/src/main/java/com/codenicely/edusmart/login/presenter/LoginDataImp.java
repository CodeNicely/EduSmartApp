package com.codenicely.edusmart.login.presenter;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.login.LoginCallback;
import com.codenicely.edusmart.login.data.LoginDataResponse;
import com.codenicely.edusmart.login.model.LoginBaseClassHelper;
import com.codenicely.edusmart.login.view.LoginView;

/**
 * Created by ramya on 12/10/16.
 */

public class LoginDataImp implements LoginData {
    private LoginBaseClassHelper loginBaseClassHelper;
    private LoginView login;

    public LoginDataImp(LoginView login, LoginBaseClassHelper loginBaseClassHelper) {
        this.login = login;
        this.loginBaseClassHelper = loginBaseClassHelper;
    }


    @Override
    public void getLoginData(String user_id, String password,int login_type) {
        login.showProgressBar(true);
        loginBaseClassHelper.loginData(user_id, password,login_type, new LoginCallback() {
            @Override
            public void onLoginSuccess(LoginDataResponse loginResponse) {
                if(loginResponse.isSuccess()) {
                    login.showLoginStatus(loginResponse);
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
