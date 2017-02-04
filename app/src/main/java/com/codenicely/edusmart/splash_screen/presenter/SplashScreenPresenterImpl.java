package com.codenicely.edusmart.splash_screen.presenter;

import android.content.pm.PackageManager;

import com.codenicely.edusmart.splash_screen.SplashScreenCallback;
import com.codenicely.edusmart.splash_screen.model.SplashScreenHelperClass;
import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;
import com.codenicely.edusmart.splash_screen.view.SplashScreenView;

/**
 * Created by ramya on 3/2/17.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter {
    private SplashScreenHelperClass splashScreenHelperClass;
    private SplashScreenView splashScreenView;
    public SplashScreenPresenterImpl(SplashScreenHelperClass splashScreenHelperClass,SplashScreenView splashScreenView)
    {
        this.splashScreenHelperClass=splashScreenHelperClass;
        this.splashScreenView=splashScreenView;
    }
    @Override
    public void sendFcm(String fcm) {
        splashScreenHelperClass.sendFcm(fcm, new SplashScreenCallback() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) {
                if (splashScreenData.isSuccess()) {
                    try {
                        splashScreenView.onVersionReceived(splashScreenData);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailed() {
            splashScreenView.onFailed();
            }
        });
    }
}
