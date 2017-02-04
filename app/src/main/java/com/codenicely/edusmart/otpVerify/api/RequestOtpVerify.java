package com.codenicely.edusmart.otpVerify.api;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.otpVerify.data.OtpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ramya on 13/10/16.
 */

public interface RequestOtpVerify {
    @FormUrlEncoded
    @POST(Urls.SUB_URL_VERIFY)
    Call<OtpData> getJson(@Field("otp") String otp,
                          @Field("roll_number") String mobile,
                          @Field("login_type") int login_type);

}
