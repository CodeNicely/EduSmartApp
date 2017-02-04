package com.codenicely.edusmart.login.model;

import android.util.Log;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.login.LoginCallback;
import com.codenicely.edusmart.login.api.RequestLogin;
import com.codenicely.edusmart.login.model.data.LoginDataResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 12/10/16.
 */

public class RetrofitLoginHelper implements LoginHelper {
    private static String TAG = "RetrofitLoginHelper";

    @Override
    public void loginData(String user_id, int login_type, final LoginCallback loginCallback) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Urls.BASE_URL).
//                client(client).
        addConverterFactory(GsonConverterFactory.create()).
                        build();
        RequestLogin requestLogin = retrofit.create(RequestLogin.class);
        Call<LoginDataResponse> call = requestLogin.requestLogin(user_id, login_type);
        call.enqueue(new Callback<LoginDataResponse>() {
            @Override
            public void onResponse(Call<LoginDataResponse> call, Response<LoginDataResponse> response) {
                Log.i(TAG, "Got Response " + response.body().getMessage().toString());
                loginCallback.onLoginSuccess(response.body());

            }

            @Override
            public void onFailure(Call<LoginDataResponse> call, Throwable t) {
                loginCallback.onLoginFailure(t.getMessage());
            }
        });
    }
}