package com.codenicely.edusmart.home.model.data;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListDataDetails {
    private String topic_name;
    private String topic_description;
    private int file_type;

    public HomeListDataDetails(String topic_name,String topic_description,int type)
    {
        this.topic_description=topic_description;
        this.topic_name=topic_name;
        this.file_type=type;
    }

    public String getTopic_name() {

        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_description() {
        return topic_description;
    }

    public void setTopic_description(String topic_description) {
        this.topic_description = topic_description;
    }

    public int getType() {
        return file_type;
    }

    public void setType(int type) {
        this.file_type = type;
    }
}
