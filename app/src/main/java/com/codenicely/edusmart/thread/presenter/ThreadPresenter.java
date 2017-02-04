package com.codenicely.edusmart.thread.presenter;

/**
 * Created by meghal on 4/2/17.
 */

public interface ThreadPresenter {

    void requestThreads(String access_token);

    void createThread(String access_token, String thread_name, String description, int access_level);
}
