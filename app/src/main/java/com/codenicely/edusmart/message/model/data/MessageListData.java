package com.codenicely.edusmart.message.model.data;

import java.util.List;

/**
 * Created by meghal on 3/2/17.
 */

public class MessageListData {

    private boolean success;
    private String message;
    private List<MessageData> message_list;

    public MessageListData(boolean success, String message, List<MessageData> message_list) {
        this.success = success;
        this.message = message;
        this.message_list = message_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<MessageData> getMessage_list() {
        return message_list;
    }
}
