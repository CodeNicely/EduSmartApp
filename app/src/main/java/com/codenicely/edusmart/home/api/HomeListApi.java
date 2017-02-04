package com.codenicely.edusmart.home.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.home.model.data.HomeListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by ramya on 4/2/17.
 */

public interface HomeListApi {
    @GET(Urls.SUB_URL_HOME)
    Call<HomeListData>getJson(@Query("access_token")String access_token);


}
