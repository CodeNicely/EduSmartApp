package com.codenicely.edusmart.thread.presenter;

import com.codenicely.edusmart.thread.OnThreadCreated;
import com.codenicely.edusmart.thread.OnThreadSuccess;
import com.codenicely.edusmart.thread.model.ThreadProvider;
import com.codenicely.edusmart.thread.model.data.CreateThreadData;
import com.codenicely.edusmart.thread.model.data.ThreadData;
import com.codenicely.edusmart.thread.view.ThreadView;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadPresenterImpl implements ThreadPresenter {

    private ThreadView threadView;
    private ThreadProvider threadProvider;


    public ThreadPresenterImpl(ThreadView threadView, ThreadProvider threadProvider) {
        this.threadView = threadView;
        this.threadProvider = threadProvider;
    }


    @Override
    public void requestThreads(String access_token) {
        threadView.showLoader(true);
        threadProvider.requestThreads(access_token, new OnThreadSuccess() {
            @Override
            public void onSuccess(ThreadData threadData) {

                if (threadData.isSuccess()) {
                    threadView.setData(threadData);
                } else {
                    threadView.showMessage(threadData.getMessage());
                }
                threadView.showLoader(false);
            }

            @Override
            public void onFailed(String message) {
                threadView.showLoader(false);
                threadView.showMessage(message);
            }
        });

    }

    @Override
    public void createThread(String access_token, String thread_name, String description, int access_level) {

        threadView.showDialog(true);
        threadProvider.createThread(access_token, thread_name, description, access_level, new OnThreadCreated() {
            @Override
            public void onSuccess(CreateThreadData createThreadData) {
                if (createThreadData.isSuccess()) {
                    threadView.showMessage(createThreadData.getMessage());
                    threadView.reloadThreads();
                } else {
                    threadView.showMessage(createThreadData.getMessage());
                }
                threadView.showDialog(false);
            }

            @Override
            public void onFailed(String message) {

                threadView.showDialog(false);
                threadView.showMessage(message);
            }
        });


    }
}
