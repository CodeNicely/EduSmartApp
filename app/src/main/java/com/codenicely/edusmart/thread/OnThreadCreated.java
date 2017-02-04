package com.codenicely.edusmart.thread;

import com.codenicely.edusmart.thread.model.data.CreateThreadData;

/**
 * Created by meghal on 4/2/17.
 */

public interface OnThreadCreated {


    void onSuccess(CreateThreadData createThreadData);
    void onFailed(String message);


}
