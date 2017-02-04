package com.codenicely.edusmart.spot_upload.presenter;


import android.net.Uri;
import android.util.Log;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.spot_upload.model.UploadProvider;
import com.codenicely.edusmart.spot_upload.model.data.UploadData;
import com.codenicely.edusmart.spot_upload.view.UploadView;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.edusmart.helper.FileProvider.requestNewFile;


/**
 * Created by meghal on 11/10/16.
 */

public class UploadPresenterImpl implements UploadPresenter {


    private static final String TAG = "UploadPresenterImpl";
    private UploadView uploadView;
    private UploadProvider uploadProvider;
    private Observable<UploadData> uploadDataObservable;
    private Subscription subscription;

    public UploadPresenterImpl(UploadView uploadView, UploadProvider uploadProvider) {
        this.uploadView = uploadView;
        this.uploadProvider = uploadProvider;
    }

    @Override
    public void openCamera() {
        File image = requestNewFile();

        if (uploadView.checkPermissionForCamera()) {
            uploadView.fileFromPath(image.getPath());
            uploadView.showCamera();
        } else {
            if (uploadView.requestCameraPermission()) {
                uploadView.fileFromPath(image.getPath());

                uploadView.showCamera();
            }
        }

    }

    @Override
    public void openGallery() {

        if (uploadView.checkPermissionForGallery()) {

            uploadView.showGalleryImage();
        } else {

            if (uploadView.requestGalleryPermission()) {
                uploadView.showGalleryImage();
            }
        }

    }

    @Override
    public void openGalleryFile() {

        if (uploadView.checkPermissionForGallery()) {

            uploadView.showGalleryFile();
        } else {

            if (uploadView.requestGalleryPermission()) {
                uploadView.showGalleryFile();
            }
        }

    }


    @Override
    public void uploadData(String access_token, String title, String description, String type,String subject_id, Uri fileUri) {


        uploadView.showLoader(true);
        try {
            uploadDataObservable = uploadProvider.requestUpload(access_token, title, description,
                    type,subject_id, fileUri);
            Log.i(TAG, "Value of Observable" + uploadDataObservable.toString());
            subscription = uploadDataObservable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UploadData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    uploadView.showLoader(false);
                    uploadView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
                    e.printStackTrace();
                }

                @Override
                public void onNext(UploadData spotUploadData) {

                    Log.i(TAG, "Response " + spotUploadData.toString());
                    if (spotUploadData.isSuccess()) {
                        uploadView.showLoader(false);
//                        uploadView.showMessage(spotUploadData.getMessage());
                        uploadView.showDialog("Uploaded Successfully", "Your Spot has been uploaded successfully we will be reaching you soon");
                    } else {
                        uploadView.showLoader(false);
                        uploadView.showMessage(spotUploadData.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
