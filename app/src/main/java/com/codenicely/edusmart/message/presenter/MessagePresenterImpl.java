package com.codenicely.edusmart.message.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.FileProvider;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.message.OnMessageReceived;
import com.codenicely.edusmart.message.OnMessageSent;
import com.codenicely.edusmart.message.model.MessageProvider;
import com.codenicely.edusmart.message.model.data.MessageListData;
import com.codenicely.edusmart.message.model.data.SendMessageData;
import com.codenicely.edusmart.message.view.MessageView;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by meghal on 3/2/17.
 */

public class MessagePresenterImpl implements MessagePresenter {

    private MessageView messageView;
    private MessageProvider messageProvider;
    private Observable<SendMessageData> sendMessageDataObservable;
    private Subscription subscription;


    public MessagePresenterImpl(MessageView messageView, MessageProvider messageProvider) {
        this.messageView = messageView;
        this.messageProvider = messageProvider;
    }

    @Override
    public void requestMessages(String access_token, int thread_id, int last_message_id) {

//        messageView.showLoader(true);
        messageProvider.requestMessage(access_token, thread_id, last_message_id, new OnMessageReceived() {
            @Override
            public void onSuccess(MessageListData messageListData) {

                if (messageListData.isSuccess()) {
                    messageView.showLoader(false);
                    messageView.updateMessageList(messageListData);
                }
            }

            @Override
            public void onFailure(String message) {

                messageView.showLoader(false);
            }
        });

    }

    @Override
    public void sendMessage(String access_token,int thread_id, String message) {

        messageProvider.sendMessage(access_token,thread_id, message, new OnMessageSent() {
            @Override
            public void onSuccess(SendMessageData sendMessageData) {

                if (sendMessageData.isSuccess()) {
//                    messageView.showLoader(false);
//                    messageView.getMessage();
                }
            }

            @Override
            public void onFailure(String message) {

//                messageView.showLoader(false);
                messageView.showMessage("Failed");
            }
        });


    }


    @Override
    public void openCamera() {
        File image = FileProvider.requestNewFile();

        if (messageView.checkPermissionForCamera()) {
            messageView.fileFromPath(image.getPath());
            messageView.showCamera();
        } else {
            if (messageView.requestCameraPermission()) {
                messageView.fileFromPath(image.getPath());

                messageView.showCamera();
            }
        }

    }

    @Override
    public void openGallery() {

        if (messageView.checkPermissionForGallery()) {

            messageView.showGallery();
        } else {

            if (messageView.requestGalleryPermission()) {
                messageView.showGallery();
            }
        }

    }


    @Override
    public void sendImageMessage(String access_token, Uri imageUri) {


        messageView.showLoader(true);
        try {
            sendMessageDataObservable = messageProvider.sendImageMessage(access_token, imageUri);
            Log.i(TAG, "Value of Observable" + sendMessageDataObservable.toString());
            subscription = sendMessageDataObservable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SendMessageData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    messageView.showLoader(false);
                    messageView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
                    e.printStackTrace();
                }

                @Override
                public void onNext(SendMessageData spotUploadData) {

                    Log.i(TAG, "Response " + spotUploadData.toString());
                    if (spotUploadData.isSuccess()) {
                        messageView.showLoader(false);
//                        messageView.showMessage(spotUploadData.getMessage());
//                        messageView.showDialog("Uploaded Successfully","Your Spot has been uploaded successfully we will be reaching you soon");
                    } else {
                        messageView.showLoader(false);
                        messageView.showMessage(spotUploadData.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
