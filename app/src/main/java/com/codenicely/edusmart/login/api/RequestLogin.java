package com.codenicely.edusmart.login.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.login.model.data.LoginDataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ramya on 12/10/16.
 */

public interface RequestLogin {
    @FormUrlEncoded
    @POST(Urls.SUB_URL_LOGIN)
    Call<LoginDataResponse> requestLogin(@Field("roll_no") String user_id,
                                         @Field("password") String password,
                                         @Field("login_type") int login_type);
}
