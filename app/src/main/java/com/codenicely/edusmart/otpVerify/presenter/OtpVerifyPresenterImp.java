package com.codenicely.edusmart.otpVerify.presenter;


import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.otpVerify.OtpVerificationCallback;
import com.codenicely.edusmart.otpVerify.data.OtpData;
import com.codenicely.edusmart.otpVerify.model.OtpVerifyHelperClass;
import com.codenicely.edusmart.otpVerify.view.OtpView;

/**
 * Created by ramya on 13/10/16.
 */

public class OtpVerifyPresenterImp implements OtpVerifyPresenter {
    private OtpView otpView;
    private OtpVerifyHelperClass otpVerifyHelperClass;

    public OtpVerifyPresenterImp(OtpView otpView, OtpVerifyHelperClass otpVerifyHelperClass) {
        this.otpView = otpView;
        this.otpVerifyHelperClass = otpVerifyHelperClass;
    }

    @Override
    public void otpData(String otp, String roll_number, int login_type) {
        otpView.showProgressBar(true);
        otpVerifyHelperClass.getOtpResponse(otp, roll_number, login_type, new OtpVerificationCallback() {
            @Override
            public void onOtpVerified(OtpData otpData) {

                if (otpData.isSuccess()) {
                    otpView.onOtpVerified(otpData);

                } else {
                    otpView.showMessage(otpData.getMessage());
                }
                otpView.showProgressBar(false);
            }

            @Override
            public void onFailure(String error) {
                otpView.showProgressBar(false);
                otpView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });

    }
}
