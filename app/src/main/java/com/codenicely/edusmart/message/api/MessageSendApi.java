package com.codenicely.edusmart.message.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.message.model.data.SendMessageData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 3/2/17.
 */

public interface MessageSendApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_SEND_MESSAGE)
    Call<SendMessageData>
    sendMessage(@Field("access_token") String access_token,
                @Field("thread_id") int thread_id,
                @Field("message") String message);


}
