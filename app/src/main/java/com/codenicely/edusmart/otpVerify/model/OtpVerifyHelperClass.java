package com.codenicely.edusmart.otpVerify.model;


import com.codenicely.edusmart.otpVerify.OtpVerificationCallback;

/**
 * Created by ramya on 13/10/16.
 */

public interface OtpVerifyHelperClass {

    void getOtpResponse(String otp, String roll_number, int login_type,
                        OtpVerificationCallback otpVerificationCallback);
}
