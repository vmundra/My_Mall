package com.example.mymall;

public class ProductSpecificationModel {

    private String featureName;
    private String featureValue;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    public ProductSpecificationModel(String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }
}
