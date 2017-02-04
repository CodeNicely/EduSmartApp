package com.codenicely.edusmart.splash_screen.model.data;

/**
 * Created by ramya on 3/2/17.
 */

public class SplashScreenData {
    private boolean success;
    private String message;
    private int version;
    private boolean compulsory_update;

    public SplashScreenData(boolean success, String message, int version, boolean compulsory_update) {
        this.success = success;
        this.message = message;
        this.version = version;
        this.compulsory_update = compulsory_update;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getVersion_code() {
        return version;
    }

    public boolean isCompulsory_update() {
        return compulsory_update;
    }
}
