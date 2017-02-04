package com.codenicely.edusmart.message.model.data;

/**
 * Created by meghal on 3/2/17.
 */

public class SendMessageData {

private boolean success;
    private String message;

    public SendMessageData(boolean success, String message) {
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
