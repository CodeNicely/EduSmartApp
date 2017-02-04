package com.codenicely.edusmart.message.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.message.model.data.SendMessageData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by meghal on 12/10/16.
 */

public interface ImageSendApi {

    @Multipart
    @POST(Urls.SUB_URL_SEND_MESSAGE)
    Observable<SendMessageData> sendImageMessage(@Part("access_token") RequestBody access_token, @Part("message_type") RequestBody message_type, @Part MultipartBody.Part image);
}
