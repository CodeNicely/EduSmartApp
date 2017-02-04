package com.codenicely.edusmart.welcome_screen.model;

import android.util.Log;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.welcome_screen.model.data.WelcomeData;
import com.codenicely.edusmart.welcome_screen.SlidesRequestCallback;
import com.codenicely.edusmart.welcome_screen.api.RequestScreen;

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

public class RetrofitScreenSlider  implements ScreenSliderHelper{
    private static final String TAG = "RetrofitSlidesProvider";

        @Override
    public void getSlides(final SlidesRequestCallback slidesRequest) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .build();


            Retrofit retrofit= new Retrofit.Builder().baseUrl(Urls.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            final RequestScreen requestScreen =retrofit.create(RequestScreen.class);
            Call<WelcomeData> call= requestScreen.requestWelcome();
            call.enqueue(new Callback<WelcomeData>() {
                @Override
                public void onResponse(Call<WelcomeData> call, Response<WelcomeData> response) {
                    slidesRequest.onSuccess(response.body());
                    Log.d("Response recieved",response.message());
                }

                @Override
                public void onFailure(Call<WelcomeData> call, Throwable t) {
                    slidesRequest.onFailure();
                    Log.d("error",t.getMessage());
                }
            });

    }
}
