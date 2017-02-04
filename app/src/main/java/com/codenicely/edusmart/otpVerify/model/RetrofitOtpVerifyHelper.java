package com.codenicely.edusmart.otpVerify.model;


import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.otpVerify.OtpVerificationCallback;
import com.codenicely.edusmart.otpVerify.api.RequestOtpVerify;
import com.codenicely.edusmart.otpVerify.data.OtpData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 13/10/16.
 */

public class RetrofitOtpVerifyHelper implements OtpVerifyHelperClass {
    private static String TAG = "RetrofitOtpVerifyHelper";


    @Override
    public void getOtpResponse(final String otp, String roll_number,int login_type, final OtpVerificationCallback otpVerificationCallback) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Urls.BASE_URL).client(client).
                addConverterFactory(GsonConverterFactory.create()).build();
        final RequestOtpVerify requestOtpVerify = retrofit.create(RequestOtpVerify.class);

        Call<OtpData> call = requestOtpVerify.getJson(otp, roll_number,login_type);
        call.enqueue(new Callback<OtpData>() {
            @Override
            public void onResponse(Call<OtpData> call, Response<OtpData> response) {

                otpVerificationCallback.onOtpVerified(response.body());

            }

            @Override
            public void onFailure(Call<OtpData> call, Throwable t) {
                t.printStackTrace();
                otpVerificationCallback.onFailure(t.getMessage());
            }
        });

    }
}
