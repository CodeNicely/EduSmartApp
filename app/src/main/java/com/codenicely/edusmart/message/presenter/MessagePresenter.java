package com.codenicely.edusmart.message.presenter;

import android.net.Uri;

/**
 * Created by meghal on 3/2/17.
 */

public interface MessagePresenter {

    void requestMessages(String access_token, String thread_id, int last_message_id);

    void sendMessage(String access_token, String message);

    void openCamera();

    /**
     * This function s called from view if user chooses to select images already present in gallery
     */
    void openGallery();


    void sendImageMessage(String access_token, Uri imageUri);
}
