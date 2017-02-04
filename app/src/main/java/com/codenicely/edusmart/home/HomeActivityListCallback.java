package com.codenicely.edusmart.home;

import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.home.model.data.HomeListDataDetails;

import java.util.List;

/**
 * Created by ramya on 4/2/17.
 */

public interface HomeActivityListCallback {
    void onSuccess(HomeListData homeListData);
    void onFailure();
}
