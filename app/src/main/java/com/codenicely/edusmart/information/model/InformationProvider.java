package com.codenicely.edusmart.information.model;

import com.codenicely.edusmart.information.OnInformationReceived;

/**
 * Created by meghal on 4/2/17.
 */

public interface InformationProvider {

    void requestInformation(String access_token, int subject_id, int type,
                            OnInformationReceived onInformationReceived);

}
