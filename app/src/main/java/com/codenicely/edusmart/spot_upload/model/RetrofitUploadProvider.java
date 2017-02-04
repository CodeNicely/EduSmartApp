package com.codenicely.edusmart.spot_upload.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.edusmart.helper.Urls;
import com.codenicely.edusmart.helper.utils.BitmapUtils;
import com.codenicely.edusmart.helper.utils.FileUtils;
import com.codenicely.edusmart.helper.utils.UriUtils;
import com.codenicely.edusmart.spot_upload.api.UploadApi;
import com.codenicely.edusmart.spot_upload.model.data.UploadData;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by meghal on 11/10/16.
 */

public class RetrofitUploadProvider implements UploadProvider {

    private Retrofit retrofit;
    private UploadApi uploadApi;
    private Context context;
    public RetrofitUploadProvider(Context context){

        this.context=context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        uploadApi =retrofit.create(UploadApi.class);

    }


    @Override
    public Observable<UploadData> requestUpload(String access_token,
                                                String title,
                                                String description,
                                                String type,
                                                String subject_id,
                                                Uri fileUri) throws IOException {


        RequestBody access_token1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), access_token);

        RequestBody title1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), title);
        RequestBody description1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), description);
        RequestBody type1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), type);
        RequestBody subject_id1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), subject_id);


/*


*/
        if(fileUri!=null) {
                File imageFile=new File(fileUri.getPath());
//            File imageFile= FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, fileUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("file", imageFile.getName(), fbody);

            return uploadApi.uploadSpotDetails(access_token1,title1,description1,type1,subject_id1, image);
        }

        return null;
    }
}
