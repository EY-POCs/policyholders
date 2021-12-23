package com.example.policyholder.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("policyHolders")
public class PolicyHolderDTO {
    @Id
    private String policyHolderId;
    private String name;
    private String address;
    private String creditCardNumber;

    public String getPolicyHolderId() {
        return policyHolderId.toUpperCase();
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId = policyHolderId.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
