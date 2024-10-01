package com.bcb.core.persistence.model;

import com.bcb.core.domain.model.CustomerPlanTypeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bcb_customer_plan")
public class CustomerPlanEntity {

    @Id
    @SequenceGenerator(name = "bcb_customer_plan_customer_plan_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bcb_customer_plan_customer_plan_id_seq")
    @Column(name = "customer_plan_id", updatable = false)
    private Long id;

    @Column(name = "plan_type")
    @Enumerated(EnumType.STRING)
    private CustomerPlanTypeEnum planType;

    @Column(name = "amount")
    private BigDecimal amount;

    public CustomerPlanEntity() {
    }

    public CustomerPlanEntity(Long id, CustomerPlanTypeEnum planType, BigDecimal amount) {
        this.id = id;
        this.planType = planType;
        this.amount = amount;
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
