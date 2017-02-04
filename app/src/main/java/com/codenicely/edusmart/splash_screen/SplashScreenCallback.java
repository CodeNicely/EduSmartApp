package com.codenicely.edusmart.splash_screen;

import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;

/**
 * Created by ramya on 3/2/17.
 */

public interface SplashScreenCallback {
    void onSuccess(SplashScreenData splashScreenData);
    void onFailed();
}
