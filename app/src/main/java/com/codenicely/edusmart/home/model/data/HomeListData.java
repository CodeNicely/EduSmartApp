package com.codenicely.edusmart.home.model.data;

import java.util.List;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListData {

    private String message;
    private boolean success;
    private List<HomeListDataDetails> home_data_list;

    public HomeListData(String message, boolean success, List<HomeListDataDetails> home_data_list) {
        this.message = message;
        this.success = success;
        this.home_data_list = home_data_list;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<HomeListDataDetails> getHome_data_list() {
        return home_data_list;
    }

    public void setHome_data_list(List<HomeListDataDetails> home_data_list) {
        this.home_data_list = home_data_list;
    }

}
