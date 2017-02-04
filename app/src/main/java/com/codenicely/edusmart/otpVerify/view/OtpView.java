package com.codenicely.edusmart.otpVerify.view;


import com.codenicely.edusmart.otpVerify.data.OtpData;

/**
 * Created by ramya on 13/10/16.
 */

public interface OtpView {
    void showProgressBar(boolean show);
    void showMessage(String message);
    void onOtpVerified(OtpData otpData);

}
