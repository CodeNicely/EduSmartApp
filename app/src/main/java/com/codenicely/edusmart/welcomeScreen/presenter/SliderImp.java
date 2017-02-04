package com.codenicely.edusmart.welcomeScreen.presenter;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.welcomeScreen.Data.WelcomeData;
import com.codenicely.edusmart.welcomeScreen.SlidesRequestCallback;
import com.codenicely.edusmart.welcomeScreen.model.ScreenSliderHelper;
import com.codenicely.edusmart.welcomeScreen.view.WelcomeView;
/**
 * Created by ramya on 12/10/16.
 */

public class SliderImp implements Slider {
    private WelcomeView welcomeView;
    private ScreenSliderHelper screenSliderHelper;

    public SliderImp(WelcomeView welcomeView, ScreenSliderHelper screenSliderHelper) {
        this.screenSliderHelper = screenSliderHelper;
        this.welcomeView = welcomeView;
    }


    @Override
    public void getSlides() {
        welcomeView.showProgressBar(true);
        screenSliderHelper.getSlides(new SlidesRequestCallback() {
            @Override
            public void onSuccess(WelcomeData welcomeData) {
                if (welcomeData.isSuccess()) {
                    welcomeView.showProgressBar(false);
                    welcomeView.setSlides(welcomeData.getSlider_list());
                } else {
                    welcomeView.showProgressBar(true);
                    welcomeView.showError("error loading pages");
                }
            }

            @Override
            public void onFailure() {

                welcomeView.showProgressBar(false);
                welcomeView.showError(MyApplication.getContext().getResources().getString(R.string.failure_message));

            }
        });


    }
}
