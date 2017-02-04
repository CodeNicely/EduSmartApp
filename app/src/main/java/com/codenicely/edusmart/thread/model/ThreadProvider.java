package com.codenicely.edusmart.thread.model;

import com.codenicely.edusmart.thread.OnThreadCreated;
import com.codenicely.edusmart.thread.OnThreadSuccess;

/**
 * Created by meghal on 4/2/17.
 */

public interface ThreadProvider {

    void requestThreads(String access_token, OnThreadSuccess onThreadSuccess);

    void createThread(String access_token, String thread_name,
                      String description, int access_level, OnThreadCreated onThreadCreated);

}
