package com.codenicely.edusmart.otpVerify.data;

/**
 * Created by ramya on 13/10/16.
 */

public class OtpData {
    private String message;
    private String access_token;
    private boolean success;

    public OtpData(String message, String access_token, boolean success)
    {
        this.message=message;
        this.success=success;

        this.access_token=access_token;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }



    public String getAccess_token() {
        return access_token;
    }
}
