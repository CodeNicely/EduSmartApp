package com.codenicely.edusmart.information.presenter;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.information.OnInformationReceived;
import com.codenicely.edusmart.information.model.InformationProvider;
import com.codenicely.edusmart.information.view.InformationView;

/**
 * Created by meghal on 4/2/17.
 */

public class InformationPresenterImpl implements InformationPresenter {

    private InformationView informationView;
    private InformationProvider informationProvider;

    public InformationPresenterImpl(InformationView informationView, InformationProvider informationProvider) {
        this.informationView = informationView;
        this.informationProvider = informationProvider;
    }


    @Override
    public void requestInformation(String access_token, String subject_id, int type) {
        informationView.showLoader(true);
        informationProvider.requestInformation(access_token, subject_id, type, new OnInformationReceived() {
            @Override
            public void onSuccess(HomeListData homeListData) {
                informationView.showLoader(false);
                if (homeListData.isSuccess()) {
                    informationView.setData(homeListData);
                } else {
                    informationView.showMessage(homeListData.getMessage());

                }

            }

            @Override
            public void onFailed(String message) {

                informationView.showLoader(false);
                informationView.showMessage(MyApplication.getContext().getResources().
                        getString(R.string.failure_message));


            }
        });


    }
}
