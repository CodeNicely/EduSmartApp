package com.codenicely.edusmart.message.model;

import android.net.Uri;

import com.codenicely.edusmart.message.OnMessageReceived;
import com.codenicely.edusmart.message.OnMessageSent;
import com.codenicely.edusmart.message.model.data.SendMessageData;

import java.io.IOException;

import rx.Observable;


/**
 * Created by meghal on 3/2/17.
 */

public interface MessageProvider {

    void requestMessage(String access_token, int thread_id, int last_message_id,
                        OnMessageReceived onMessageReceived);

    void sendMessage(String access_token, int thread_id, String message,
                     OnMessageSent onMessageSent);

    Observable<SendMessageData> sendImageMessage(String access_token,
                                                 Uri imageUri) throws IOException;

}
