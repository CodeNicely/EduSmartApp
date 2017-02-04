package com.codenicely.edusmart.information;

import com.codenicely.edusmart.home.model.data.HomeListData;

/**
 * Created by meghal on 4/2/17.
 */

public interface OnInformationReceived {

    void onSuccess(HomeListData homeListData);
    void onFailed(String message);


}
