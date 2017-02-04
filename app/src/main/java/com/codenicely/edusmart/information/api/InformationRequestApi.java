package com.codenicely.edusmart.information.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.home.model.data.HomeListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghal on 4/2/17.
 */

public interface InformationRequestApi {


    @GET(Urls.SUB_URL_DATA)
    Call<HomeListData> getJson(@Query("access_token") String access_token,
                               @Query("subject_id")String subject_id,
                               @Query("type") int type);
}
