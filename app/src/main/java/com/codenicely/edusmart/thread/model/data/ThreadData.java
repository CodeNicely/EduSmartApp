package com.codenicely.edusmart.thread.model.data;

import java.util.List;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadData {

    private boolean success;
    private String message;
    private List<ThreadDetails> threadDetailsList;

    public ThreadData(boolean success, String message, List<ThreadDetails> threadDetailsList) {
        this.success = success;
        this.message = message;
        this.threadDetailsList = threadDetailsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ThreadDetails> getThreadDetailsList() {
        return threadDetailsList;
    }
}
