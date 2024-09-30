package com.bcb.core.domain.model;

public enum CustomerPlanTypeEnum {
    PREPAID("PREPAID"),
    POSTPAID("POSTPAID");

    private final String planType;
    CustomerPlanTypeEnum(String planType) {
        this.planType = planType;
    }

    public String getPlanType() {
        return planType;
    }
}
