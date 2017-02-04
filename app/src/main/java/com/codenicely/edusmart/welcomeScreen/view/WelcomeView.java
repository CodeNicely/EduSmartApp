package com.codenicely.edusmart.welcomeScreen.view;

import com.codenicely.edusmart.welcomeScreen.Data.WelcomePageDetails;

import java.util.List;

/**
 * Created by ramya on 12/10/16.
 */

public interface WelcomeView {
    void showProgressBar(boolean show);
    void setSlides(List<WelcomePageDetails> welcomePageDetailsList);

    void showError(String error);
}
