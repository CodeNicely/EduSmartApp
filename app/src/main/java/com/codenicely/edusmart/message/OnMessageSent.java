package com.codenicely.edusmart.message;


import com.codenicely.edusmart.message.model.data.SendMessageData;

/**
 * Created by meghal on 3/2/17.
 */

public interface OnMessageSent {

    void onSuccess(SendMessageData sendMessageData);
    void onFailure(String message);


}
