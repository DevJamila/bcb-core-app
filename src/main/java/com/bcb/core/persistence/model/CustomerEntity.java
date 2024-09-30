package com.bcb.core.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bcb_customer")
public class CustomerEntity {

    @Id
    @SequenceGenerator(name = "bcb_customer_customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bcb_customer_customer_id_seq")
    @Column(name = "customer_id", updatable = false)
    private Long id;

    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_email")
    private String email;
    @Column(name = "customer_phone")
    private String phone;
    @Column(name = "customer_document")
    private String document;
    @Column(name = "is_company")
    private Boolean isCompany;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_document")
    private String companyDocument;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerPlanEntity customerPlan;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String email, String phone, String document, Boolean isCompany, String companyName, String companyDocument, CustomerPlanEntity customerPlan) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.isCompany = isCompany;
        this.companyName = companyName;
        this.companyDocument = companyDocument;
        this.customerPlan = customerPlan;
    }

    public CustomerEntity(Long id, String name, String email, String phone, String document, Boolean isCompany, String companyName, String companyDocument, CustomerPlanEntity customerPlan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.isCompany = isCompany;
        this.companyName = companyName;
        this.companyDocument = companyDocument;
        this.customerPlan= customerPlan;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

    public Boolean isCompany() {
        return isCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDocument() {
        return companyDocument;
    }

    public CustomerPlanEntity getCustomerPlan() {
        return customerPlan;
    }

    public void setCustomerPlan(CustomerPlanEntity customerPlan) {
        this.customerPlan = customerPlan;
    }
}
