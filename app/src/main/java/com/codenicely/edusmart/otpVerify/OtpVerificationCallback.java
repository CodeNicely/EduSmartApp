package com.codenicely.edusmart.otpVerify;


import com.codenicely.edusmart.otpVerify.data.OtpData;

/**
 * Created by ramya on 13/10/16.
 */

public interface OtpVerificationCallback {

    void onOtpVerified(OtpData otpData);

    void onFailure(String error);
}
