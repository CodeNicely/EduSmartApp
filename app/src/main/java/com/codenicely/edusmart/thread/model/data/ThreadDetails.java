package com.codenicely.edusmart.thread.model.data;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadDetails {


    private int thread_id;
    private String thread_name;
    private String thread_description;
    private String created;
    private String updated;

    public ThreadDetails(int thread_id, String thread_name, String thread_description, String created, String updated) {
        this.thread_id = thread_id;
        this.thread_name = thread_name;
        this.thread_description = thread_description;
        this.created = created;
        this.updated = updated;
    }

    public int getThread_id() {
        return thread_id;
    }

    public String getThread_name() {
        return thread_name;
    }

    public String getThread_description() {
        return thread_description;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}

