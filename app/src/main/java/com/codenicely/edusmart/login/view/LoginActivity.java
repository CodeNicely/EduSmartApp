package com.codenicely.edusmart.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.home.view.HomeActivity;
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

    @BindView(R.id.input_password)
    TextView editTextPassword;

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
        if(getIntent()!=null)
        {
            login_type=getIntent().getIntExtra(Keys.KEY_LOGIN,0);
        }

        sharedPrefs = new SharedPrefs(this);
        initialise();
    }

    public void initialise() {

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==10) {
                    hideKeyboard();
                }
                }


            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    public void proceed(View v) {
        user_id = editTextUserId.getText().toString();
        password= editTextPassword.getText().toString();
        if (user_id.isEmpty() || password.isEmpty()) {
            showProgressBar(false);
            showError("Fields cannot be empty");
        } else {
            loginPresenter = new LoginPresenterImp(this, new RetrofitLoginHelper());
            loginPresenter.getLoginData(user_id, password,login_type);
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
        if (loginDataResponse.getLogin_type() == 0) {
            sharedPrefs.setUserName(user_id);
            sharedPrefs.setAccessToken(loginDataResponse.getAccess_token());
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();

        } else if(loginDataResponse.getLogin_type()==1){
            sharedPrefs.setUserName(user_id);
            sharedPrefs.setAccessToken(loginDataResponse.getAccess_token());
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();

        }
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
