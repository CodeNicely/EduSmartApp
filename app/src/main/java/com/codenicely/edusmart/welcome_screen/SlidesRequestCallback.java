package com.codenicely.edusmart.welcome_screen;

import com.codenicely.edusmart.welcome_screen.model.data.WelcomeData;

/**
 * Created by ramya on 12/10/16.
 */

public interface SlidesRequestCallback {
    void onSuccess(WelcomeData welcomeData);
    void onFailure();
}
