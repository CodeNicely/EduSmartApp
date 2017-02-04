package com.codenicely.edusmart.spot_upload.model;

import android.net.Uri;

import com.codenicely.edusmart.spot_upload.model.data.UploadData;

import java.io.IOException;

import rx.Observable;


/**
 * Created by meghal on 11/10/16.
 */

public interface UploadProvider {




    Observable<UploadData> requestUpload(String access_token, String title, String description,
                                         String type,String subject_id,
                                         Uri fileUri) throws IOException;

}
