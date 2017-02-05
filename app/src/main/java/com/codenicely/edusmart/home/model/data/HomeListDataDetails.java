package com.codenicely.edusmart.home.model.data;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListDataDetails {
    private int id;
    private String title;
    private String description;
    private int file_type;
    private String deadline;
    private String file_url;
    private String timestamp;
    private int card_type;
    private String author;
    private int count;


    public HomeListDataDetails(int id, String title, String description, int file_type,
                               String deadline, String file_url, String timestamp, int card_type, String author, int count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.file_type = file_type;
        this.deadline = deadline;
        this.file_url = file_url;
        this.timestamp = timestamp;
        this.card_type = card_type;
        this.author = author;
        this.count = count;
    }

    public int getId() {
        return id;
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

    public String getAuthor() {
        return author;
    }

    public int getCount() {
        return count;
    }
}
