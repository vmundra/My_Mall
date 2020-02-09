package com.example.mymall;

public class RewardModel {

    private String title;
    private String expiryDate;
    private String coupenBody;


    public RewardModel(String title, String expiryDate, String coupenBody) {
        this.title = title;
        this.expiryDate = expiryDate;
        this.coupenBody = coupenBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCoupenBody() {
        return coupenBody;
    }

    public void setCoupenBody(String coupenBody) {
        this.coupenBody = coupenBody;
    }
}
