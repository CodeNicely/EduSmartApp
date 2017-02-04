package com.codenicely.edusmart.home.model.data;

import java.util.List;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListData {

    private String message;
    private boolean success;

    private List<HomeListDataDetails> homeListDataDetailsList;

    public HomeListData(String message,boolean success,List<HomeListDataDetails>homeListDataDetailses)
    {
        this.message=message;
        this.success=success;
        this.homeListDataDetailsList=homeListDataDetailses;
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

    public List<HomeListDataDetails> getHomeListDataDetailsList() {
        return homeListDataDetailsList;
    }

    public void setHomeListDataDetailsList(List<HomeListDataDetails> homeListDataDetailsList) {
        this.homeListDataDetailsList = homeListDataDetailsList;
    }

}
