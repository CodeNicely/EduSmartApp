package com.codenicely.edusmart.otpVerify.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.otpVerify.data.OtpData;
import com.codenicely.edusmart.otpVerify.model.RetrofitOtpVerifyHelper;
import com.codenicely.edusmart.otpVerify.presenter.OtpVerifyPresenter;
import com.codenicely.edusmart.otpVerify.presenter.OtpVerifyPresenterImp;
import com.codenicely.edusmart.home.view.HomeActivity;
import com.codenicely.edusmart.login.view.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends AppCompatActivity implements OtpView {

    private String otp, roll_number;
    private OtpVerifyPresenter otpVerifyPresenter;
    private SharedPrefs sharedPrefs;

    @BindView(R.id.input_otp)
    EditText editTextOtp;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.mobile_text)
    EditText txtView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String message;
    private int login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            roll_number = bundle.getString(Keys.KEY_ROLL_NUMBER);
            otp = bundle.getString(Keys.KEY_OTP);
            message = bundle.getString(Keys.KEY_LOGIN_MESSAGE);
            login_type=bundle.getInt(Keys.KEY_LOGIN_TYPE);
        }
        sharedPrefs = new SharedPrefs(this);
        txtView.setText(message);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void proceed_verify(View v) {
        otp = editTextOtp.getText().toString();
        otpVerifyPresenter = new OtpVerifyPresenterImp(this, new RetrofitOtpVerifyHelper());
        otpVerifyPresenter.otpData(otp, roll_number,login_type );
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onOtpVerified(OtpData otpData) {


        Intent i = new Intent(this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(login_type==0){

            sharedPrefs.setTeacherLogin(true);
            sharedPrefs.setLogin(false);
        }else{
            sharedPrefs.setLogin(true);
            sharedPrefs.setTeacherLogin(false);
        }
        sharedPrefs.setAccessToken(otpData.getAccess_token());
        startActivity(i);
        finish();
    }
}
