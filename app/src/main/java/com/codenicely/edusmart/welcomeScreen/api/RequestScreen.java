package com.codenicely.edusmart.welcomeScreen.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.welcomeScreen.Data.WelcomeData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ramya on 12/10/16.
 */

public interface RequestScreen {
    @GET(Urls.SUB_URL_WELCOME)
    Call<WelcomeData> getJSON();
}
