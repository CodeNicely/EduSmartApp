package com.codenicely.edusmart.message;


import com.codenicely.edusmart.message.model.data.MessageListData;

/**
 * Created by meghal on 3/2/17.
 */

public interface OnMessageReceived {


    void onSuccess(MessageListData messageListData);

    void onFailure(String message);
}
