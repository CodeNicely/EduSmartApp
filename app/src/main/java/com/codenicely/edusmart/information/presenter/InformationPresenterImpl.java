package com.codenicely.edusmart.information.presenter;

import com.codenicely.edusmart.information.OnInformationReceived;
import com.codenicely.edusmart.information.model.InformationProvider;
import com.codenicely.edusmart.information.view.InformationView;

/**
 * Created by meghal on 4/2/17.
 */

public class InformationPresenterImpl implements InformationPresenter{

    private InformationView informationView;
    private InformationProvider informationProvider;

    public InformationPresenterImpl(InformationView informationView, InformationProvider informationProvider) {
        this.informationView = informationView;
        this.informationProvider = informationProvider;
    }


    @Override
    public void requestInformation(String access_token, String subject_id, int type) {
        informationView.showLoader(true);
        informationProvider.requestInformation(access_token,subject_id,type,new OnInformationReceived());
    }
}
