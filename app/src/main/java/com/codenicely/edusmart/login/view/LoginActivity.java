package com.codenicely.edusmart.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.otpVerify.view.OtpActivity;
import com.codenicely.edusmart.login.model.data.LoginDataResponse;
import com.codenicely.edusmart.login.model.RetrofitLoginHelper;
import com.codenicely.edusmart.login.presenter.LoginPresenter;
import com.codenicely.edusmart.login.presenter.LoginPresenterImp;
import com.codenicely.edusmart.welcome_screen.view.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {


    private String user_id, password;
    private LoginPresenter loginPresenter;
    private SharedPrefs sharedPrefs;
    int login_type;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_user_id)
    TextView editTextUserId;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (getIntent() != null) {
            login_type = getIntent().getExtras().getInt(Keys.KEY_LOGIN);
        }

        if (login_type == 0) {
            toolbar.setTitle("Teacher Login");
        } else {
            toolbar.setTitle("Student Login");

        }

        sharedPrefs = new SharedPrefs(this);
    }


    public void proceed(View v) {
        user_id = editTextUserId.getText().toString();
        if (user_id.isEmpty() ) {
            showProgressBar(false);
            showError("Fields cannot be empty");
        } else {
            loginPresenter = new LoginPresenterImp(this, new RetrofitLoginHelper());
            loginPresenter.getLoginData(user_id, login_type);
            hideKeyboard();
        }

    }


    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoginSuccess(LoginDataResponse loginDataResponse) {

        Intent i = new Intent(LoginActivity.this, OtpActivity.class);
        i.putExtra(Keys.KEY_ROLL_NUMBER, editTextUserId.getText().toString());
        i.putExtra(Keys.KEY_LOGIN_TYPE, login_type);
        i.putExtra(Keys.KEY_LOGIN_MESSAGE,loginDataResponse.getMessage());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();


    }

    @Override
    public void showError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
