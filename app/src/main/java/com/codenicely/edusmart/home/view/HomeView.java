package com.codenicely.edusmart.home.view;

import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.home.model.data.HomeListDataDetails;

import java.util.List;

/**
 * Created by ramya on 4/2/17.
 */

public interface HomeView {
    void showProgressBar(boolean show);
    void showMessage(String message);
    void setHomeList(List<HomeListDataDetails>homeListDataList);

}
