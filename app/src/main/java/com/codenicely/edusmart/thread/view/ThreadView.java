package com.codenicely.edusmart.thread.view;

import com.codenicely.edusmart.thread.model.data.ThreadData;

/**
 * Created by meghal on 4/2/17.
 */

public interface ThreadView {


    void showMessage(String message);

    void showLoader(boolean show);

    void showDialog(boolean show);

    void setData(ThreadData threadData);

    void reloadThreads();
}
