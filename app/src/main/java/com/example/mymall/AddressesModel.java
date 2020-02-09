package com.example.mymall;

public class AddressesModel {

    private String fullname;
    private String address;
    private String pincode;

    public AddressesModel(String fullname, String address, String pincode) {
        this.fullname = fullname;
        this.address = address;
        this.pincode = pincode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
