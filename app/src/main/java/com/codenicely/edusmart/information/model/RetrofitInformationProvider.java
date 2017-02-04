package com.codenicely.edusmart.information.model;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.information.OnInformationReceived;
import com.codenicely.edusmart.information.api.InformationRequestApi;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.information.OnInformationReceived;
import com.codenicely.edusmart.information.api.InformationRequestApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 4/2/17.
 */

public class RetrofitInformationProvider implements InformationProvider {

    private InformationRequestApi informationRequestApi;

    RetrofitInformationProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Urls.BASE_URL).
//                    client(client).
        addConverterFactory(GsonConverterFactory.create()).build();
        informationRequestApi = retrofit.create(InformationRequestApi.class);
    }

    @Override
    public void requestInformation(String access_token, String subject_id, int type, final OnInformationReceived onInformationReceived) {

        Call<HomeListData> homeListDataCall = informationRequestApi.getJson(access_token, subject_id, type);
        homeListDataCall.enqueue(new Callback<HomeListData>() {
            @Override
            public void onResponse(Call<HomeListData> call, Response<HomeListData> response) {


                onInformationReceived.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<HomeListData> call, Throwable t) {

                t.printStackTrace();
                onInformationReceived.onFailed(MyApplication.getContext().getResources().
                        getString(R.string.failure_message));
            }
        });
    }
}
