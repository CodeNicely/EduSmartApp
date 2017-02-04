package com.codenicely.edusmart.welcome_screen.view;

import com.codenicely.edusmart.welcome_screen.model.data.WelcomePageDetails;

import java.util.List;

/**
 * Created by ramya on 12/10/16.
 */

public interface WelcomeView {
    void showProgressBar(boolean show);
    void setSlides(List<WelcomePageDetails> welcomePageDetailsList);

    void showError(String error);
}
