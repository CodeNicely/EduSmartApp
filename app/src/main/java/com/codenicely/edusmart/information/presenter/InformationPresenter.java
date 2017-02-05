package com.codenicely.edusmart.information.presenter;

/**
 * Created by meghal on 4/2/17.
 */

public interface InformationPresenter {

    void requestInformation(String access_token, int subject_id, int type);
}
