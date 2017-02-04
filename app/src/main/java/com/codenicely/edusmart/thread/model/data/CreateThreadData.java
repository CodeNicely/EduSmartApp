package com.codenicely.edusmart.thread.model.data;

/**
 * Created by meghal on 4/2/17.
 */

public class CreateThreadData {

    private boolean success;
    private String message;

    public CreateThreadData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
