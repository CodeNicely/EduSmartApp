package com.codenicely.edusmart.welcome_screen.model.data;


import java.util.List;

/**
 * Created by ramya on 10/10/16.
 */

public class WelcomeData {
    private int id;
    private boolean success;
    private String message;
    private List<WelcomePageDetails> slider_list;

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<WelcomePageDetails> getSlider_list() {
        return slider_list;
    }

    public WelcomeData(int id, boolean success, String message, List<WelcomePageDetails> slider_list) {

        this.id = id;
        this.success = success;
        this.message = message;
        this.slider_list = slider_list;
    }
}
