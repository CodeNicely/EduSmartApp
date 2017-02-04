package com.codenicely.edusmart.message.view;


import com.codenicely.edusmart.message.model.data.MessageListData;

/**
 * Created by meghal on 3/2/17.
 */

public interface MessageView {

    void showMessage(String message);

    void showLoader(boolean show);

    void updateMessageList(MessageListData messageListData);

    void getMessage();

    /**
     * This method is for checking camera permission.
     * Applicable only for devices with Api 23 or more.
     *
     * @return
     */
    boolean checkPermissionForCamera();

    /**
     * This method is for checking gallery permission.
     * Applicable only for devices with api 23 or more.
     *
     * @return
     */
    boolean checkPermissionForGallery();

    /**
     * This function is for requesting camera permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestCameraPermission();

    /**
     * This function is for requesting gallery permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestGalleryPermission();


    /**
     * This method is called when user chooses to open camera.
     */
    void showCamera();

    /**
     * This method is called when user chooses to open gallery.
     */

    void showGallery();

    void fileFromPath(String filePath);

}
