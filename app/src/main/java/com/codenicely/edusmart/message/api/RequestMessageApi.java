package com.codenicely.edusmart.message.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.message.model.data.MessageListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghal on 3/2/17.
 */

public interface RequestMessageApi {

    @GET(Urls.URL_REQUEST_MESSAGE)
    Call<MessageListData> requestMessage(@Query("access_token") String access_token,
                                         @Query("fcm") String thread_id,
                                         @Query("last_message_id") int last_message_id);


}
