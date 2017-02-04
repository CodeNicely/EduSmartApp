package com.codenicely.edusmart.welcome_screen.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.welcome_screen.model.data.WelcomeData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ramya on 12/10/16.
 */

public interface RequestScreen {
    @GET(Urls.SUB_URL_WELCOME)
    Call<WelcomeData> requestWelcome();
}
