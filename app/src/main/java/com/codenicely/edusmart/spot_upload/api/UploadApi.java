package com.codenicely.edusmart.spot_upload.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.spot_upload.model.data.UploadData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by meghal on 12/10/16.
 */

public interface UploadApi {

    @Multipart
    @POST(Urls.SUB_URL_UPLOAD)
    Observable<UploadData> uploadSpotDetails(@Part("access_token") RequestBody access_token,
                                             @Part("title") RequestBody title,
                                             @Part("description") RequestBody description,
                                             @Part("type") RequestBody type,
                                             @Part("subject_id") RequestBody subject_id,
                                             @Part MultipartBody.Part file);
}
