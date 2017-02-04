package com.codenicely.edusmart.welcomeScreen;

import com.codenicely.edusmart.welcomeScreen.Data.WelcomeData;

/**
 * Created by ramya on 12/10/16.
 */

public interface SlidesRequestCallback {
    void onSuccess(WelcomeData welcomeData);
    void onFailure();
}
