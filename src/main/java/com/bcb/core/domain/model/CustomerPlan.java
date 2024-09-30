package com.bcb.core.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerPlan {
    private Long id;
    private CustomerPlanTypeEnum planType;
    private BigDecimal amount;

    public CustomerPlan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerPlanTypeEnum getPlanType() {
        return planType;
    }

    public void setPlanType(CustomerPlanTypeEnum planType) {
        this.planType = planType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
