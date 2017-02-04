package com.codenicely.edusmart.welcomeScreen.Data;

/**
 * Created by ramya on 14/10/16.
 */

public class WelcomePageDetails {


    private int id;
    private String image;
    private String quote;

    public WelcomePageDetails(int id, String image, String quote) {
        this.id = id;
        this.image = image;
        this.quote = quote;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getQuote() {
        return quote;
    }
}
