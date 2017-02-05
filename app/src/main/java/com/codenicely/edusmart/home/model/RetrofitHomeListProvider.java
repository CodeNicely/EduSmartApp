package com.codenicely.edusmart.home.model;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.home.HomeActivityListCallback;
import com.codenicely.edusmart.home.api.HomeListApi;
import com.codenicely.edusmart.home.model.data.HomeListData;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 4/2/17.
 */

public class RetrofitHomeListProvider implements HomeListProviderHelper {
    private HomeListApi homeListApi;

    public RetrofitHomeListProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        homeListApi = retrofit.create(HomeListApi.class);
    }

    @Override
    public void getHomeList(String access_token, String fcm, final HomeActivityListCallback homeActivityListCallback) {
        Call<HomeListData> call = homeListApi.getJson(access_token, fcm);
        call.enqueue(new Callback<HomeListData>() {
            @Override
            public void onResponse(Call<HomeListData> call, Response<HomeListData> response) {
                homeActivityListCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HomeListData> call, Throwable t) {
                homeActivityListCallback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
