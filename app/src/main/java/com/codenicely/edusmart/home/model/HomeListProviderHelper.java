package com.codenicely.edusmart.home.model;

import com.codenicely.edusmart.home.HomeActivityListCallback;

/**
 * Created by ramya on 4/2/17.
 */

public interface HomeListProviderHelper {
    void getHomeList(String access_token, HomeActivityListCallback homeActivityListCallback);
}
