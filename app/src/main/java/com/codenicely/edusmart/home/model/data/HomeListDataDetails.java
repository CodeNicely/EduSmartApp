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
    private String teacher_name;
    private String subject_name;
    private int count;

    public HomeListDataDetails(String topic_name,String topic_description,int type,String deadline,
                               String file_url,String timestamp,String teacher_name,String subject_name )
    {
        this.description =topic_description;
        this.title =topic_name;
        this.file_type=type;
        this.deadline=deadline;
        this.file_url=file_url;
        this.timestamp=timestamp;
        this.teacher_name=teacher_name;
        this.subject_name=subject_name;

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return file_type;
    }

    public void setType(int type) {
        this.file_type = type;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
