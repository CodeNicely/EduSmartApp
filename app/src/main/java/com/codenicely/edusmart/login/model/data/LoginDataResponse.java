package com.codenicely.edusmart.login.model.data;

/**
 * Created by ramya on 13/10/16.
 */

public class LoginDataResponse {
    private boolean success;
    private String message;

    public LoginDataResponse(boolean success, String message,String access_token,int login_type)
    {
        this.message=message;
        this.success=success;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
