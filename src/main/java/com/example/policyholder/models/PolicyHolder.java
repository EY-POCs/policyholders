package com.example.policyholder.models;

public class PolicyHolder {
    String policyHolderId;
    String name;
    String address;
    String creditCardNumber;
    CustomError error;
    Info info;

    public String getPolicyHolderId() {
        return policyHolderId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public CustomError getError() {
        return error;
    }

    public Info getInfo() {
        return info;
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setError(CustomError error) {
        this.error = error;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
