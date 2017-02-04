package com.codenicely.edusmart.login.model.data;

/**
 * Created by ramya on 13/10/16.
 */

public class LoginDataResponse {
    private boolean success;
    private String message;
    private String access_token;
    private int login_type;

    public LoginDataResponse(boolean success, String message,String access_token,int login_type)
    {
        this.message=message;
        this.login_type=login_type;
        this.access_token=access_token;
        this.success=success;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getLogin_type() {
        return login_type;
    }

    public void setLogin_type(int login_type) {
        this.login_type = login_type;
    }
}
