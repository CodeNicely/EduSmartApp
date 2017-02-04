package com.codenicely.edusmart.splash_screen.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ramya on 3/2/17.
 */

public interface SplashScreenApi {
    @GET(Urls.SUB_URL_SPLASH_SCREEN)
    Call<SplashScreenData> sendFcm(@Query("fcm") String fcm);
}
