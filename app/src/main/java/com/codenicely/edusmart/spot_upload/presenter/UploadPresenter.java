package com.codenicely.edusmart.spot_upload.presenter;

import android.net.Uri;

/**
 * Created by meghal on 11/10/16.
 */

public interface UploadPresenter {


    /**
     * This function is called to open camera for clicking new image
     */
    void openCamera();

    /**
     * This function s called from view if user chooses to select images already present in gallery
     */
    void openGallery();

    void openGalleryFile();



    void uploadData(String access_token, String title, String description, String type,
                    String subject_id,
                    Uri fileUri);

}
