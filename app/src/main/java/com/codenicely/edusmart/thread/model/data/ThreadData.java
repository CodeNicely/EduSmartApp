package com.codenicely.edusmart.thread.model.data;

import java.util.List;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadData {

    private boolean success;
    private String message;
    private List<ThreadDetails> data_list;

    public ThreadData(boolean success, String message, List<ThreadDetails> data_list) {
        this.success = success;
        this.message = message;
        this.data_list = data_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ThreadDetails> getData_list() {
        return data_list;
    }
}
