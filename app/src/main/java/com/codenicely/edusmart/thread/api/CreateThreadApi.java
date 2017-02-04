package com.codenicely.edusmart.thread.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.message.model.data.SendMessageData;
import com.codenicely.edusmart.thread.model.data.CreateThreadData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 4/2/17.
 */

public interface CreateThreadApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_THREAD)
    Call<CreateThreadData>
    createThread(@Field("access_token") String access_token,
                 @Field("title") String title,
                 @Field("description") String description,
                 @Field("access_level") int access_level
    );


}
