package com.codenicely.edusmart.splash_screen.model;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.splash_screen.SplashScreenCallback;
import com.codenicely.edusmart.splash_screen.api.SplashScreenApi;
import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 3/2/17.
 */

public class RetrofitSplashScreenProvider implements SplashScreenHelperClass {
    private SplashScreenApi splashScreenApi;

    public RetrofitSplashScreenProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        splashScreenApi = retrofit.create(SplashScreenApi.class);

    }

    @Override
    public void sendFcm(final SplashScreenCallback splashScreenCallback) {
        Call<SplashScreenData> call = splashScreenApi.sendFcm();
        call.enqueue(new Callback<SplashScreenData>() {
            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response) {
                splashScreenCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t) {
                splashScreenCallback.onFailed();
            }
        });

    }
}
