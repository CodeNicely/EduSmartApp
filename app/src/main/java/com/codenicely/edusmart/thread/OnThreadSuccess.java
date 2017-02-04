package com.codenicely.edusmart.thread;

import com.codenicely.edusmart.thread.model.data.ThreadData;

/**
 * Created by meghal on 4/2/17.
 */

public interface OnThreadSuccess {

    void onSuccess(ThreadData threadData);

    void onFailed(String message);

}
