package com.codenicely.edusmart.splash_screen.view;

import android.content.pm.PackageManager;

import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;

/**
 * Created by ramya on 3/2/17.
 */

public interface SplashScreenView {
    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;
    void onFailed();
}
