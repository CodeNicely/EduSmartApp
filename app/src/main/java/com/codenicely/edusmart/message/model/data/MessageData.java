package com.codenicely.edusmart.message.model.data;

/**
 * Created by meghal on 3/2/17.
 */

public class MessageData {

    private int message_id;
    private boolean owner;
    private String user_id;
    private String body;
    private String created;
    private String image;


    public MessageData(int message_id, boolean owner, String user_id, String body, String created, String image) {
        this.message_id = message_id;
        this.owner = owner;
        this.user_id = user_id;
        this.body = body;
        this.created = created;
        this.image = image;
    }

    public int getMessage_id() {
        return message_id;
    }

    public boolean isOwner() {
        return owner;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getBody() {
        return body;
    }

    public String getCreated() {
        return created;
    }

    public String getImage() {
        return image;
    }
}
