package com.codenicely.edusmart.thread.model;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.thread.OnThreadCreated;
import com.codenicely.edusmart.thread.OnThreadSuccess;
import com.codenicely.edusmart.thread.api.CreateThreadApi;
import com.codenicely.edusmart.thread.api.RequestThreadApi;
import com.codenicely.edusmart.thread.model.data.CreateThreadData;
import com.codenicely.edusmart.thread.model.data.ThreadData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 4/2/17.
 */

public class RetrofitThreadProvider implements ThreadProvider {

    private Retrofit retrofit;

    public RetrofitThreadProvider() {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @Override
    public void requestThreads(String access_token, final OnThreadSuccess onThreadSuccess) {

        RequestThreadApi requestThreadApi = retrofit.create(RequestThreadApi.class);
        Call<ThreadData> threadDataCall = requestThreadApi.requestThread(access_token);

        threadDataCall.enqueue(new Callback<ThreadData>() {
            @Override
            public void onResponse(Call<ThreadData> call, Response<ThreadData> response) {

                onThreadSuccess.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ThreadData> call, Throwable t) {
                onThreadSuccess.onFailed("Failed");
            }
        });


    }

    @Override
    public void createThread(String access_token, String thread_name, String description, int access_level, final OnThreadCreated onThreadCreated) {

        CreateThreadApi createThreadApi=retrofit.create(CreateThreadApi.class);
        Call<CreateThreadData> createThreadDataCall=createThreadApi.createThread(access_token,
                thread_name,
                description,
                access_level);

        createThreadDataCall.enqueue(new Callback<CreateThreadData>() {
            @Override
            public void onResponse(Call<CreateThreadData> call, Response<CreateThreadData> response) {


                onThreadCreated.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<CreateThreadData> call, Throwable t) {

                t.printStackTrace();
                onThreadCreated.onFailed("Failed to conect to servers");
            }
        });


    }

}
