package com.codenicely.edusmart.thread.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;
import com.codenicely.edusmart.thread.model.data.ThreadData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghal on 4/2/17.
 */

public interface RequestThreadApi {

    @GET(Urls.SUB_URL_THREAD)
    Call<ThreadData> requestThread(@Query("access_token") String access_token);

}
