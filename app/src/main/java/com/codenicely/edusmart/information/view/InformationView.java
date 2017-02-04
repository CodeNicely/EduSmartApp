package com.codenicely.edusmart.information.view;

import com.codenicely.edusmart.home.model.data.HomeListData;

/**
 * Created by meghal on 4/2/17.
 */

public interface InformationView {

    void showLoader(boolean show);
    void showMessage(String message);
    void setData(HomeListData homeListData);
}
