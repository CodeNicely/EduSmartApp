package com.codenicely.edusmart.home.model.data;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListDataDetails {
    private String title;
    private String description;
    private int file_type;
    private String deadline;
    private String file_url;
    private String timestamp;
    private int card_type;
    private String teacher_name;
    private String subject_name;
    private int count;

    public HomeListDataDetails(String title, String description, int file_type, String deadline, String file_url, String timestamp, int card_type, String teacher_name, String subject_name, int count) {
        this.title = title;
        this.description = description;
        this.file_type = file_type;
        this.deadline = deadline;
        this.file_url = file_url;
        this.timestamp = timestamp;
        this.card_type = card_type;
        this.teacher_name = teacher_name;
        this.subject_name = subject_name;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getFile_type() {
        return file_type;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getCard_type() {
        return card_type;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public int getCount() {
        return count;
    }
}
