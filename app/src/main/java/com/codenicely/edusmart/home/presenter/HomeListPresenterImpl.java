package com.codenicely.edusmart.home.presenter;

import com.codenicely.edusmart.home.HomeActivityListCallback;
import com.codenicely.edusmart.home.model.HomeListProviderHelper;
import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.home.view.HomeView;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListPresenterImpl implements HomeListPresenter {
    private HomeListProviderHelper homeListProviderHelper;
    private HomeView homeView;
    public HomeListPresenterImpl(HomeListProviderHelper homeListProviderHelper,HomeView homeView)
    {
        this.homeView=homeView;
        this.homeListProviderHelper=homeListProviderHelper;
    }

    @Override
    public void getHomeList(String access_token) {
        homeView.showProgressBar(true);
        homeListProviderHelper.getHomeList(access_token, new HomeActivityListCallback() {
            @Override
            public void onSuccess(HomeListData homeListData) {
                if(homeListData.isSuccess())
                {
                    homeView.setHomeList(homeListData.getHome_data_list());
                }
                homeView.showProgressBar(false);
            }

            @Override
            public void onFailure() {
                homeView.showMessage("please try again we couldn't connect to our servers");
            }
        });
    }
}
