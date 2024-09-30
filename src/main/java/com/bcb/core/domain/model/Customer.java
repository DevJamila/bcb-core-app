package com.bcb.core.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private Boolean isCompany;
    private String companyName;
    private String companyDocument;

    private CustomerPlan customerPlan;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Boolean isCompany() {
        return isCompany;
    }

    public void setCompany(Boolean company) {
        isCompany = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDocument() {
        return companyDocument;
    }

    public void setCompanyDocument(String companyDocument) {
        this.companyDocument = companyDocument;
    }

    public CustomerPlan getCustomerPlan() {
        return customerPlan;
    }

    public void setCustomerPlan(CustomerPlan customerPlan) {
        this.customerPlan = customerPlan;
    }
}
