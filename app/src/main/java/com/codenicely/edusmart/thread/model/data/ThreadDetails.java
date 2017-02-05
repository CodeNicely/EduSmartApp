package com.codenicely.edusmart.thread.model.data;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadDetails {


    private int thread_id;
    private String title;
    private String description;
    private String created;
    private String author;
    private int access_level;

    public ThreadDetails(int thread_id, String title, String description, String created, String author, int access_level) {
        this.thread_id = thread_id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.author = author;
        this.access_level = access_level;
    }

    public int getThread_id() {
        return thread_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getAuthor() {
        return author;
    }

    public int getAccess_level() {
        return access_level;
    }
}

