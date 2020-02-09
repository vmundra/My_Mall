package com.example.mymall;

public class MyOrderItemModel {

    private int productImage;
    private String productTitle;
    private String deliveryStatus;

    private int rating;

    public int getProductImage() {
        return productImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public MyOrderItemModel(int productImage,int rating, String productTitle, String deliveryStatus) {
        this.productImage = productImage;
        this.rating= rating;
        this.productTitle = productTitle;
        this.deliveryStatus = deliveryStatus;
    }
}
