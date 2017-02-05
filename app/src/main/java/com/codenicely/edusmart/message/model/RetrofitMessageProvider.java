package com.codenicely.edusmart.message.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.helper.utils.BitmapUtils;
import com.codenicely.edusmart.helper.utils.FileUtils;
import com.codenicely.edusmart.helper.utils.UriUtils;
import com.codenicely.edusmart.message.OnMessageReceived;
import com.codenicely.edusmart.message.OnMessageSent;
import com.codenicely.edusmart.message.api.ImageSendApi;
import com.codenicely.edusmart.message.api.MessageSendApi;
import com.codenicely.edusmart.message.api.RequestMessageApi;
import com.codenicely.edusmart.message.model.data.MessageListData;
import com.codenicely.edusmart.message.model.data.SendMessageData;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by meghal on 3/2/17.
 */

public class RetrofitMessageProvider implements MessageProvider {

    private Retrofit retrofit;
    private Context context;

    public RetrofitMessageProvider(Context context) {
        this.context = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    @Override
    public void requestMessage(String access_token, int thread_id, int last_message_id, final OnMessageReceived onMessageReceived) {

        RequestMessageApi requestMessageApi;
        requestMessageApi = retrofit.create(RequestMessageApi.class);

        Call<MessageListData> messageListDataCall = requestMessageApi.requestMessage(access_token, thread_id, last_message_id);
        messageListDataCall.enqueue(new Callback<MessageListData>() {
            @Override
            public void onResponse(Call<MessageListData> call, Response<MessageListData> response) {
                onMessageReceived.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MessageListData> call, Throwable t) {

                t.printStackTrace();
                onMessageReceived.onFailure("Failed");
            }
        });

    }

    @Override
    public void sendMessage(String access_token, int thread_id, String message, final OnMessageSent onMessageSent) {

        MessageSendApi messageSendApi = retrofit.create(MessageSendApi.class);
        Call<SendMessageData> sendMessageDataCall = messageSendApi.sendMessage(access_token, thread_id, message);
        sendMessageDataCall.enqueue(new Callback<SendMessageData>() {
            @Override
            public void onResponse(Call<SendMessageData> call, Response<SendMessageData> response) {
                onMessageSent.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SendMessageData> call, Throwable t) {

                onMessageSent.onFailure("Failed");
                t.printStackTrace();
            }
        });
    }


    @Override
    public Observable<SendMessageData> sendImageMessage(String access_token, Uri imageUri) throws IOException {

        ImageSendApi imageSendApi = retrofit.create(ImageSendApi.class);

        RequestBody access_token1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), access_token);


        RequestBody message_type1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), Keys.MESSAGE_TYPE_IMAGE);


        if (imageUri != null) {
            //    File imageFile=new File(imageUri.getPath());
            File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

            return imageSendApi.sendImageMessage(access_token1, message_type1, image);
        }

        return null;


    }
}
