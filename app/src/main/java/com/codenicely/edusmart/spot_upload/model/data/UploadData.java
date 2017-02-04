package com.codenicely.edusmart.spot_upload.model.data;

/**
 * Created by meghal on 12/10/16.
 */

public class UploadData {


    boolean success;
    String message;

    public UploadData(boolean success, String message) {
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
